package aut.bme.moviestore.data.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "movie")
class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    var id: String?,

    @Column(name = "title")
    var title: String,

    @Column(name = "release_date")
    var releaseDate: LocalDate?,

    @Column(name = "director")
    var director: String?
)




