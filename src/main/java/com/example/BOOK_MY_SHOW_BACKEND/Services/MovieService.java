package com.example.BOOK_MY_SHOW_BACKEND.Services;
import com.example.BOOK_MY_SHOW_BACKEND.Models.MovieEntity;
import com.example.BOOK_MY_SHOW_BACKEND.Repository.MovieRepository;
import com.example.BOOK_MY_SHOW_BACKEND.RequestDto.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){

        //Convert Dto to Entity layer for saving it to the Database.
        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();

        movieRepository.save(movie);

        return "Movie Added successfully";
    }


}