package com.example.moviedb.data

import com.example.moviedb.model.Genre
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDetails
import com.example.moviedb.model.ProductionCompany
import com.example.moviedb.model.ProductionCountry
import com.example.moviedb.model.SpokenLanguage

class HardCodedMovieRepository: IMovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                adult = false,
                backdropPath = "/qFfWFwfaEHzDLWLuttWiYq7Poy2.jpg",
                genreIds = listOf(10767),
                id = 2261,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Tonight Show Starring Johnny Carson",
                overview = "The Tonight Show Starring Johnny Carson is a talk show hosted by Johnny Carson under The Tonight Show franchise from 1962 to 1992. It originally aired during late-night. For its first ten years, Carson's Tonight Show was based in New York City with occasional trips to Burbank, California; in May 1972, the show moved permanently to Burbank, California. In 2002, The Tonight Show Starring Johnny Carson was ranked #12 on TV Guide's 50 Greatest TV Shows of All Time.",
                popularity = 667.7367,
                posterPath = "/oA8QVTGlAN511uCAMDN60aVQUs1.jpg",
                firstAirDate = "1962-10-01",
                name = "The Tonight Show Starring Johnny Carson",
                voteAverage = 7.41,
                voteCount = 72,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    )
                )
            ),
            Movie(
                adult = false,
                backdropPath = "/7VO04TtL1jIT6XOPs9u4jdB8KaB.jpg",
                genreIds = listOf(35, 10767),
                id = 59941,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Tonight Show Starring Jimmy Fallon",
                overview = "After Jay Leno's second retirement from the program, Jimmy Fallon stepped in as his permanent replacement. After 42 years in Los Angeles the program was brought back to New York.",
                popularity = 674.375,
                posterPath = "/g4amxJvtpnY79J77xeamnAEUO8r.jpg",
                firstAirDate = "2014-02-17",
                name = "The Tonight Show Starring Jimmy Fallon",
                voteAverage = 5.9,
                voteCount = 327,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    )
                )
            ),
            Movie(
                adult = false,
                backdropPath = "/kA50bkSC6bw5ZkutYrN9sLD9CZ9.jpg",
                genreIds = listOf(10767, 35),
                id = 22980,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Watch What Happens Live with Andy Cohen",
                overview = "Bravo network executive Andy Cohen discusses pop culture topics with celebrities and reality show personalities.",
                popularity = 615.3319,
                posterPath = "/onSD9UXfJwrMXWhq7UY7hGF2S1h.jpg",
                firstAirDate = "2009-07-16",
                name = "Watch What Happens Live with Andy Cohen",
                voteAverage = 5.0,
                voteCount = 62,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    ),
                    Genre(
                        id = 35,
                        name = "Comedy"
                    )
                )
            ),
            Movie(
                adult = false,
                backdropPath = "/gMMnf8VRg3Z98WaFmOLr9Jk8pIs.jpg",
                genreIds = listOf(35, 10767),
                id = 63770,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Late Show with Stephen Colbert",
                overview = "Stephen Colbert brings his signature satire and comedy to The Late Show with Stephen Colbert, the #1 show in late night, where he talks with an eclectic mix of guests about what is new and relevant in the worlds of politics, entertainment, business, music, technology, and more. Featuring bandleader Jon Batiste with his band Stay Human, the Emmy Award-nominated show is broadcast from the historic Ed Sullivan Theater. Stephen Colbert, Chris Licht, Tom Purcell, and Jon Stewart are executive producers. Barry Julien and Denise Rehrig serve as co-executive producers.must watch",
                popularity = 509.9508,
                posterPath = "/9jkThAGYj2yp8jsS6Nriy5mzKFT.jpg",
                firstAirDate = "2015-09-08",
                name = "The Late Show with Stephen Colbert",
                voteAverage = 6.5,
                voteCount = 296,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    ),
                    Genre(
                        id = 35,
                        name = "Comedy"
                    )
                )
            ),
            Movie(
                adult = false,
                backdropPath = "/7VO04TtL1jIT6XOPs9u4jdB8KaB.jpg",
                genreIds = listOf(35, 10767),
                id = 59941,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Tonight Show Starring Jimmy Fallon",
                overview = "After Jay Leno's second retirement from the program, Jimmy Fallon stepped in as his permanent replacement. After 42 years in Los Angeles the program was brought back to New York.",
                popularity = 674.375,
                posterPath = "/g4amxJvtpnY79J77xeamnAEUO8r.jpg",
                firstAirDate = "2014-02-17",
                name = "The Tonight Show Starring Jimmy Fallon",
                voteAverage = 5.9,
                voteCount = 327,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    )
                )
            ),
            Movie(
                adult = false,
                backdropPath = "/kA50bkSC6bw5ZkutYrN9sLD9CZ9.jpg",
                genreIds = listOf(10767, 35),
                id = 22980,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Watch What Happens Live with Andy Cohen",
                overview = "Bravo network executive Andy Cohen discusses pop culture topics with celebrities and reality show personalities.",
                popularity = 615.3319,
                posterPath = "/onSD9UXfJwrMXWhq7UY7hGF2S1h.jpg",
                firstAirDate = "2009-07-16",
                name = "Watch What Happens Live with Andy Cohen",
                voteAverage = 5.0,
                voteCount = 62,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    ),
                    Genre(
                        id = 35,
                        name = "Comedy"
                    )
                )
            ),
            Movie(
                adult = false,
                backdropPath = "/gMMnf8VRg3Z98WaFmOLr9Jk8pIs.jpg",
                genreIds = listOf(35, 10767),
                id = 63770,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Late Show with Stephen Colbert",
                overview = "Stephen Colbert brings his signature satire and comedy to The Late Show with Stephen Colbert, the #1 show in late night, where he talks with an eclectic mix of guests about what is new and relevant in the worlds of politics, entertainment, business, music, technology, and more. Featuring bandleader Jon Batiste with his band Stay Human, the Emmy Award-nominated show is broadcast from the historic Ed Sullivan Theater. Stephen Colbert, Chris Licht, Tom Purcell, and Jon Stewart are executive producers. Barry Julien and Denise Rehrig serve as co-executive producers.must watch",
                popularity = 509.9508,
                posterPath = "/9jkThAGYj2yp8jsS6Nriy5mzKFT.jpg",
                firstAirDate = "2015-09-08",
                name = "The Late Show with Stephen Colbert",
                voteAverage = 6.5,
                voteCount = 296,
                genres = listOf(
                    Genre(
                        id = 10767,
                        name = "Talk"
                    ),
                    Genre(
                        id = 35,
                        name = "Comedy"
                    )
                )
            ),
        )
    }

    override suspend fun getMovie(id: Int): MovieDetails {
        return MovieDetails(
            adult = false,
            backdropPath = "/TXSxV23MWYkezZ3219gtgcSX6n.jpg",
            budget = 4000000,
            genres = listOf(
                Genre(id = 12, name = "Adventure"),
                Genre(id = 16, name = "Animation"),
                Genre(id = 14, name = "Fantasy")
            ),
            homepage = "",
            id = 123,
            imdbId = "tt0077869",
            originCountry = listOf("US", "GB"),
            originalLanguage = "en",
            originalTitle = "The Lord of the Rings",
            overview = "The Fellowship of the Ring embark on a journey to destroy the One Ring and end Sauron's reign over Middle-earth.",
            popularity = 4.9445,
            posterPath = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg",
            productionCompanies = listOf(
                ProductionCompany(id = 286, logoPath = null, name = "Fantasy Films", originCountry = ""),
                ProductionCompany(id = 4921, logoPath = null, name = "Bakshi Productions", originCountry = ""),
                ProductionCompany(id = 141322, logoPath = null, name = "Saul Zaentz Film Productions", originCountry = ""),
                ProductionCompany(id = 60, logoPath = "/4YldLvCWQL9VIAHQ2Fu3Ffkh9Si.png", name = "United Artists", originCountry = "US")
            ),
            productionCountries = listOf(
                ProductionCountry(iso31661 = "GB", name = "United Kingdom"),
                ProductionCountry(iso31661 = "US", name = "United States of America")
            ),
            releaseDate = "1978-11-15",
            revenue = 30500000,
            runtime = 132,
            spokenLanguages = listOf(
                SpokenLanguage(englishName = "English", iso6391 = "en", name = "English")
            ),
            status = "Released",
            tagline = "Fantasy...beyond your imagination",
            title = "The Lord of the Rings",
            video = false,
            voteAverage = 6.6,
            voteCount = 961
        )
    }


}