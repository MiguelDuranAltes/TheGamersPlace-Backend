package com.thegamersplace.domain.service;

import com.thegamersplace.domain.entity.*;
import com.thegamersplace.domain.exception.NotFoundException;
import com.thegamersplace.domain.exception.OperationNotAllowed;
import com.thegamersplace.domain.exception.UserLoginExistsException;
import com.thegamersplace.infrastructure.repository.VideogameRepository;
import com.thegamersplace.infrastructure.web.controller.dto.UserDTOPrivate;
import com.thegamersplace.infrastructure.repository.UserRepository;
import com.thegamersplace.infrastructure.security.SecurityUtils;
import com.thegamersplace.infrastructure.web.converter.ReviewConverter;
import com.thegamersplace.infrastructure.web.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final VideogameRepository videogameRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, VideogameRepository videogameRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.videogameRepository = videogameRepository;
    }

    public User createBasicUser(String login, String password, UserRole userRole) throws OperationNotAllowed, UserLoginExistsException {
        //if buscar por login y si existe lanzar excepcion
        if(userRepository.findByLogin(login).isPresent()){
            throw new UserLoginExistsException(login);
        }
        if(password.length() < 5){
            throw new OperationNotAllowed("Password must be at least 6 characters long");
        }if(!password.matches(".*[@#$%&].*")) {
            throw new OperationNotAllowed("Password must contain at least one of the following characters: @#$%&");
        }

        return userRepository.save(new User(login,passwordEncoder.encode(password), userRole));
    }

    public User createUser(String login, String password, String imageName, String name, String city, UserRole userRole) throws OperationNotAllowed, UserLoginExistsException {
        //if buscar por login y si existe lanzar excepcion
        if(userRepository.findByLogin(login).isPresent()){
            throw new UserLoginExistsException(login);
        }
        if(password.length() < 5){
            throw new OperationNotAllowed("Password must be at least 6 characters long");
        }if(!password.matches(".*[@#$%&].*")) {
            throw new OperationNotAllowed("Password must contain at least one of the following characters: @#$%&");
        }

        return userRepository.save(new User(login,passwordEncoder.encode(password), imageName, name, city, userRole));
    }

    public User updateUser(User user) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(user.getLogin());
        if(userOptional.isEmpty()){
            throw new NotFoundException(user.getLogin(), User.class);
        }
        User userBd = userOptional.get();
        userBd.setImageURL(user.getImageURL());
        userBd.setName(user.getName());
        userBd.setCity(user.getCity());
        return userRepository.save(userBd);
    }

    public UserDTOPrivate getCurrentUserWithAuthority() {
        String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            return new UserDTOPrivate(userRepository.findByLogin(currentUserLogin).get());
        }
        return null;
    }

    public com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType getUserByLogin(String login) throws NotFoundException {
        User user;
        user = userRepository.findByLogin(login).orElse(null);
        if(user == null){
            throw new NotFoundException(login, User.class);
        }
        Set<Review> reviews = user.getReviews().stream()
                .filter(review -> review.getComment() != null && !review.getComment().equals(""))
                .collect(Collectors.toSet());
        user.setReviews(reviews);

        com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType userInfo = UserConverter.toUserInfoGraphqlType(user);
        userInfo.setVideogamesVoted(user.getReviews().size());
        return userInfo;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Collection<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<com.thegamersplace.infrastructure.graphql.types.ImageGraphqlType> getAllImages() {
        return ProfilePhotoURL.getAllImages();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean updateBlocked(String login) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if(userOptional.isEmpty()){
            throw new NotFoundException(login, User.class);
        }
        User user = userOptional.get();

        user.setBlocked(!user.getBlocked());
        userRepository.save(user);
        return true;
    }

    public Boolean deleteUser(String login) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if(userOptional.isEmpty()){
            throw new NotFoundException(login, User.class);
        }
        userRepository.delete(userOptional.get());
        return true;
    }

    public Collection<VideogameCard> addVideogameToFavourites(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        user.getFavourites().add(new VideogameCard(videogame));
        userRepository.save(user);
        return user.getFavourites();
    }

    public Collection<VideogameCard> removeVideogameFromFavourites(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        user.getFavourites().remove(new VideogameCard(videogame));
        userRepository.save(user);
        return user.getFavourites();
    }

    public Boolean isOnFavourites(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        return user.getFavourites().contains(new VideogameCard(videogame));
    }

    public Collection<VideogameCard> getFavourites(String login) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(login, User.class);
        }
        User user = userOptional.get();
        return user.getFavourites();
    }

    public Collection<VideogameCard> addVideogameToPlayed(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        user.getPlayed().add(new VideogameCard(videogame));
        userRepository.save(user);
        return user.getPlayed();
    }

    public Collection<VideogameCard> removeVideogameFromPlayed(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        user.getPlayed().remove(new VideogameCard(videogame));
        userRepository.save(user);
        return user.getPlayed();
    }

    public Boolean isOnPlayed(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        return user.getPlayed().contains(new VideogameCard(videogame));
    }

    public Collection<VideogameCard> getPlayed(String login) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(login, User.class);
        }
        User user = userOptional.get();
        return user.getPlayed();
    }

    public Collection<Integer> addVideogameToWatched(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(id);
        if(videogameOptional.isEmpty()){
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Videogame videogame = videogameOptional.get();
        user.addWatched(videogame.getId());
        userRepository.save(user);
        return user.getWatched();
    }

    public VideogameList addVideogameToList(String listName, Integer idVideogame) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(idVideogame);
        if (videogameOptional.isEmpty()) {
            throw new NotFoundException(idVideogame.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        VideogameList videogameList = user.getVideogameLists().get(listName);
        if(videogameList==null){
            throw new NotFoundException(listName, VideogameList.class);
        }
        Videogame videogame = videogameOptional.get();
        videogameList.getVideogames().add(new VideogameCard(videogame));
        userRepository.save(user);
        return videogameList;
    }

    public VideogameList removeVideogameFromList(String listName, Integer idVideogame) throws NotFoundException {
        Optional<Videogame> videogameOptional = videogameRepository.findById(idVideogame);
        if (videogameOptional.isEmpty()) {
            throw new NotFoundException(idVideogame.toString(), Videogame.class);
        }
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        VideogameList videogameList = user.getVideogameLists().get(listName);
        if(videogameList==null){
            throw new NotFoundException(listName, VideogameList.class);
        }
        Videogame videogame = videogameOptional.get();
        videogameList.getVideogames().remove(new VideogameCard(videogame));
        userRepository.save(user);
        return videogameList;
    }

    public List<String> showAllVideogameList(String login) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(login, User.class);
        }
        User user = userOptional.get();
        User currentUser = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Map<String, VideogameList> videogameLists;

        if (user.equals(currentUser)) {
            videogameLists = user.getVideogameLists();
        } else {
            videogameLists = user.getVideogameLists().entrySet().stream()
                    .filter(entry -> entry.getValue().getPublicView()) // Filtrar solo las listas públicas
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return videogameLists.values().stream()
                .map(VideogameList::getName) // Extraer solo el atributo "name"
                .collect(Collectors.toList());
    }

    public VideogameList getVideogameList(String login, String listName) throws NotFoundException, OperationNotAllowed {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(login, User.class);
        }
        User user = userOptional.get();
        User currentUser = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        VideogameList videogameList = user.getVideogameLists().get(listName);
        if(videogameList == null){
            throw new NotFoundException(listName, VideogameList.class);
        }
        if (!videogameList.getPublicView() && !user.equals(currentUser)) {
            throw new OperationNotAllowed("Trying to read private list");
        }
        return videogameList;
    }

    public VideogameList createVideogameList(String listName, Boolean isPublic) {
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();

        // Asegúrate de que el nombre de la lista sea único
        if (user.getVideogameLists().containsKey(listName)) {
            throw new IllegalArgumentException("Ya existe una lista con este nombre.");
        }

        VideogameList newList = new VideogameList();
        newList.setName(listName);
        newList.setVideogames(new HashSet<>());
        newList.setPublicView(isPublic);

        user.getVideogameLists().put(listName, newList);
        userRepository.save(user);
        return newList;
    }


    public Boolean removeVideogameList(String listName) throws NotFoundException {
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();

        if (!user.getVideogameLists().containsKey(listName)) {
            throw new NotFoundException(listName, VideogameList.class);
        }

        user.getVideogameLists().remove(listName);
        userRepository.save(user);

        return true;
    }


    public VideogameList changePublicView(String listName) throws NotFoundException {
        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();
        VideogameList videogameList = user.getVideogameLists().get(listName);
        if(videogameList==null){
            throw new NotFoundException(listName, VideogameList.class);
        }
        videogameList.setPublicView(!videogameList.getPublicView());
        userRepository.save(user);
        return videogameList;
    }

    public User addReview(com.thegamersplace.infrastructure.graphql.types.ReviewInputGraphqlType review) throws NotFoundException {
        Optional<User> userOpt = userRepository.findByLogin(review.getLogin());
        if (userOpt.isEmpty()) {
            throw new NotFoundException(review.getLogin(), User.class);
        }
        User user = userOpt.get();

        Optional<Videogame> videogameOpt = videogameRepository.findBySlug(review.getVideogame());
        if (videogameOpt.isEmpty()) {
            throw new NotFoundException(review.getVideogame(), Videogame.class);
        }

        user.getReviews().add(ReviewConverter.toReview(review));
        userRepository.save(user);

        return user;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public User removeReview(com.thegamersplace.infrastructure.graphql.types.ReviewInfoInputGraphqlType reviewInput) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(reviewInput.getLogin());
        if(userOptional.isEmpty()){
            throw new NotFoundException(reviewInput.getLogin(), User.class);
        }
        User user = userOptional.get();

        for(Review review : user.getReviews()){
            if(review.getVideogame_slug().equals(reviewInput.getVideogame())){
                review.setComment(null);
                break;
            }
        }
        userRepository.save(user);
        return user;
    }

    public Integer getReview(String videogame, String login){
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (userOpt.isEmpty()) {
            return 0;
        }
        User user = userOpt.get();

        for(Review review : user.getReviews()){
            if(review.getVideogame_slug().equals(videogame)){
                return review.getRating();
            }
        }
        return 0;
    }
}
