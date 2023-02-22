package com.wrseven.movieapps.utils

import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.resources.online.response.Movie
import com.wrseven.movieapps.resources.online.response.MovieDetailResponse
import com.wrseven.movieapps.resources.online.response.Tv
import com.wrseven.movieapps.resources.online.response.TvDetailResponse

@Suppress("UNCHECKED_CAST")
object DummyData {
    fun getDummyOnlineMovie(): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                title = "Suckseed",
                overview = "\tPed (Jirayu La-ongmanee) was a shy boy who had never listened to music until introduced to the world of Pop and Rock by would-be childhood crush Ern (Nattasha Nualjam). Ern soon left for Bangkok, however, and it is six years later in their final school year when she is re-introduced to Ped and his brash best friend Koong (Pachara Chirathivat). Koong convinces Ped, along with schoolmate Ex (Thawat Pornrattanaprasert), to form a band, partly to be cool and attract girls, partly as an attempt by Koong to try and one-up his popular twin brother Kay. Their musical talents aren't great, but that doesn't stop them from trying. However, when Ern decides to lend them her outstanding guitar skills, Ped and Koong's shared attraction to her puts a strain on the band's survival, as well as their friendship.",
                poster = "/kgJ5VPugFupZnxQfzSPWuORm6nS.jpg",
                backdrop = "/kgJ5VPugFupZnxQfzSPWuORm6nS.jpg",
                year = "2011",
                rating = 7.0
            ),

            Movie(
                id = 2,
                title = "A Little Thing Called Love",
                overview = "\tLife of a 14 year old girl Nam, who falls in love with her senior of tenth grade P'Shone and tries desperately to win his attention.",
                poster = "/3Noa9UTonvWixKaKP5D6UzhhJlY.jpg",
                backdrop = "/3Noa9UTonvWixKaKP5D6UzhhJlY.jpg",
                year = "2010",
                rating = 7.9
            ),

            Movie(
                id = 3,
                title = "One Day",
                overview = "\tDenchai is a geeky 30-year-old IT officer whose existence is only acknowledged when his colleagues need tech support. Denchai's mundane world is flipped upside down when he meets a new girl in the Marketing department titled Nui, who makes him feel valued once again. Denchai wish for Nui to be his girl for just one day. During a company trip, Nui has an accident and wakes up with a temporary memory loss disorder, which lasts for just one day. Denchai decides to lie to his dream girl by telling her that he is her boyfriend and that they had plans to travel around Hokkaido together. Would you risk everything, just to be in love for just one day?",
                poster = "/l1uyqzfoLnfP3JC5WBY3WuU4nbZ.jpg",
                backdrop = "/l1uyqzfoLnfP3JC5WBY3WuU4nbZ.jpg",
                year = "2016",
                rating = 7.7
            ),

            Movie(
                id = 4,
                title = "The Teacher's Diary",
                overview = "\tIt follows the story of two lonely teachers, a male (Song) and a female (Ann). They were assigned at the same rural school in the Chiang Mai area, but a year apart. The girl being assigned first, attempted to write her thoughts in a diary, being stuck out in boonies. One day she has to be transferred to another school and left the diary. The male teacher came by as a substitute and saw the diary, thus, falling in love with the unacquainted writer. He also wrote his thoughts in it. The guy also left and the lady returned and saw that there are added articles in the diary. She also fell in love with him but now having a hard time looking for the boy. Will the two ever meet?",
                poster = "/6xjPYpjJ0NEG8l3o3uQLCrG4lmz.jpg",
                backdrop = "/6xjPYpjJ0NEG8l3o3uQLCrG4lmz.jpg",
                year = "2014",
                rating = 7.6
            ),

            Movie(
                id = 5,
                title = "Hello Stranger",
                overview = "\tDuring the Songkran festival, two strangers who had never met before end up going home together, perhaps finding love.",
                poster = "/40WFyDGGqDE13ml6daAGPfyeNiw.jpg",
                backdrop = "/40WFyDGGqDE13ml6daAGPfyeNiw.jpg",
                year = "2010",
                rating = 7.2
            ),

            Movie(
                id = 6,
                title = "Heart Attack",
                overview = "\tA hard-worker freelancer who falls in love with a doctor. While his life is getting worse every second. He must find the way to release his life to the beyond of everything.",
                poster = "/nvoLmCPXeQyEupP2SSbkSziDvbo.jpg",
                backdrop = "/nvoLmCPXeQyEupP2SSbkSziDvbo.jpg",
                year = "2015",
                rating = 6.8
            ),

            Movie(
                id = 7,
                title = "Bad Genius",
                overview = "\tLynn, a genius student at a prestigious school, runs a business of cheating to pass competitive university entrance exams. When this activity was exposed, he began to regret his actions.",
                poster = "/i7RmV32lt6e2S3gKP27taINpiLP.jpg",
                backdrop = "/i7RmV32lt6e2S3gKP27taINpiLP.jpg",
                year = "2017",
                rating = 7.9
            ),

            Movie(
                id = 8,
                title = "Pee Mak",
                overview = "\tMak's friends just want to protect him, but his wife Nak won't let a small thing like her own death get in the way of true love in this horror-comedy.",
                poster = "/jIhCR7kuoy6WTENPZCNsDJfgLtX.jpg",
                backdrop = "/jIhCR7kuoy6WTENPZCNsDJfgLtX.jpg",
                year = "2013",
                rating = 7.2
            ),

            Movie(
                id = 9,
                title = "ATM: Er Rak Error",
                overview = "\tSua and Jib are like any other couple in this world except for one exception: for the past 5 years they have kept their relationship a secret since the bank where they are employed has a strict \"No Fraternization\" policy. It's not a big deal until they decide to get married. The only problem? Which one of these two Type-A overachievers will put marriage before a career and resign? With neither willing to take the leap of faith they both turn incident into opportunity when an ATM glitch in Chonburi province cashes out over \$130,000 baht. The terms are simple: whoever is able to recover the money first gets to keep their job. The couple will turn into no holds barred competitors. Who will literally \"go big\" in their career or \"go home\" in this romantic comedy about what two people won't do for each other in the title of love? It's the age old battle of the sexes but this time around how can victory be easily declared in a war.",
                poster = "/1Du7nW0uv5ETYQ6M9sqpysekWtm.jpg",
                backdrop = "/1Du7nW0uv5ETYQ6M9sqpysekWtm.jpg",
                year = "2012",
                rating = 7.6
            ),

            Movie(
                id = 10,
                title = "I Fine Thank You Love You",
                overview = "\tYim goes to English school to keep his Japanese girlfriend in this Thai romantic comedy.",
                poster = "/xUh33slN3QQVko2c7HybnPQgN6C.jpg",
                backdrop = "/xUh33slN3QQVko2c7HybnPQgN6C.jpg",
                year = "2014",
                rating = 6.9
            )
        )
    }

    fun getDummyOnlineTv(): List<Tv> = listOf(
        Tv(
            id = 1,
            name = "Kisah Untuk Geri",
            overview = "\tThis \"story for geri\" tells the story of a Garuda high school student titled Dinda and Geri. Dinda is known as the queen bee of The Satan gang and a child of a member of the DPR, Geri is a student who is quite famous, because of his mischief at school. are sworn enemies, the enmity began during the School Orientation Period. Dinda, who became the Primadona at her school, had a luxurious lifestyle, was always praised, the inversely proportional to her father being a suspect in a corruption case. Until finally her living conditions changed quickly, fell in an instant. All the attention she liked was lost. The only way to restore it all is to ask Geri to be his girlfriend so that he can be his protector and at the same time become a material for revenge against Jia, the enemy of Dinda. Then in the end Dinda understands that with Geri is injury and disaster for him and is the starting gate to the pain of heartbreak.",
            poster = "/mzQnuRZtjQNLEcCf8IKTjlFNePi.jpg",
            backdrop = "/mzQnuRZtjQNLEcCf8IKTjlFNePi.jpg",
            year = "2021",
            rating = 8.5
        ),

        Tv(
            id = 2,
            name = "Start-up",
            overview = "\tYoung entrepreneurs aspiring to launch virtual dreams into reality compete for success and love in the cutthroat world of Korea's high-tech industry.",
            poster = "/hxJQ3A2wtreuWDnVBbzzXI3YlOE.jpg",
            backdrop = "/hxJQ3A2wtreuWDnVBbzzXI3YlOE.jpg",
            year = "2020",
            rating = 8.4
        ),

        Tv(
            id = 3,
            name = "The Queen's Gambit",
            overview = "\tIn a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.",
            poster = "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            backdrop = "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            year = "2020",
            rating = 8.7
        ),

        Tv(
            id = 4,
            name = "Put Your Head on My Shoulder",
            overview = "\tAs Si Tu Mo's graduation is nearing, she is confused about her future plans. Her ordinary days are suddenly shaken up when the genius Physics student Gu Wei Yi appears in her life. The two accidentally end up living together and chaos begins.",
            poster = "/iuX0TRkczgbkd1tcJD01lvTWQRl.jpg",
            backdrop = "/iuX0TRkczgbkd1tcJD01lvTWQRl.jpg",
            year = "2019",
            rating = 7.8
        ),

        Tv(
            id = 5,
            name = "It's Okay Not to be Okay",
            overview = "\tDesperate to escape from his emotional baggage and the heavy responsibility he’s had all his life, a psychiatric ward worker begins to heal with help from the unexpected—a woman who writes fairy tales but doesn’t believe in them.",
            poster = "/A0HJNHiiGYJYyVavMFotnz9On6m.jpg",
            backdrop = "/A0HJNHiiGYJYyVavMFotnz9On6m.jpg",
            year = "2020",
            rating = 8.7
        ),

        Tv(
            id = 6,
            name = "Money Heist",
            overview = "\t8 thieves took hostages and locked themselves in the Spanish Money Printing Agency while the main mastermind of the crime tricked the police into putting his plan into action.",
            poster = "/fYkyF19UJdpSCMRwcQyoryBlnU3.jpg",
            backdrop = "/fYkyF19UJdpSCMRwcQyoryBlnU3.jpg",
            year = "2017",
            rating = 8.3
        ),

        Tv(
            id = 7,
            name = "Game of Thrones",
            overview = "\tSeven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            poster = "/bw6DlqljVFIinhBA7uDSNha6Lnp.jpg",
            backdrop = "/bw6DlqljVFIinhBA7uDSNha6Lnp.jpg",
            year = "2011",
            rating = 8.4
        ),

        Tv(
            id = 8,
            name = "Sherlock",
            overview = "\tA modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
            poster = "/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg",
            backdrop = "/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg",
            year = "2010",
            rating = 8.4
        ),

        Tv(
            id = 9,
            name = "Bad Genius: The Series",
            overview = "\tWhat started from exam cheating in the classroom escalated to stealing a national test paper. These are not your everyday students. They’re a reflection of the cheating that happens at every level in Thai society. Lin, a genius who cheats for revenge against the school. Hoping to reclaim the tea money her father paid to the Headmistress; Bank, a son who is dutiful to his mother but has to cheat because fate is always playing tricks with him; Pat, the son of a wealthy family who cheats because he’s pressured by his father; and Grace... it's not clear why this young girl cheats. Is it because she’s swayed by her friends?",
            poster = "/zjTamJapgSwRUQlbmlgwxgszMhG.jpg",
            backdrop = "/zjTamJapgSwRUQlbmlgwxgszMhG.jpg",
            year = "2020",
            rating = 9.1
        ),

        Tv(
            id = 10,
            name = "The Gifted",
            overview = "\tRitdha High School is not the only school with gifted students. But what makes it unique is its \"Gifted Program\". In the Gifted class, only a handful of students were chosen to be in this program, but only those who are deemed \"special\".",
            poster = "/yQGnwhqXwljiZWtnwnemm8iDieY.jpg",
            backdrop = "/yQGnwhqXwljiZWtnwnemm8iDieY.jpg",
            year = "2018",
            rating = 8.5
        )
    )

    fun getDetailMovieEntity(): MovieEntity = MovieEntity(
        id = 1,
        title = "Suckseed",
        overview = "\tPed (Jirayu La-ongmanee) was a shy boy who had never listened to music until introduced to the world of Pop and Rock by would-be childhood crush Ern (Nattasha Nualjam). Ern soon left for Bangkok, however, and it is six years later in their final school year when she is re-introduced to Ped and his brash best friend Koong (Pachara Chirathivat). Koong convinces Ped, along with schoolmate Ex (Thawat Pornrattanaprasert), to form a band, partly to be cool and attract girls, partly as an attempt by Koong to try and one-up his popular twin brother Kay. Their musical talents aren't great, but that doesn't stop them from trying. However, when Ern decides to lend them her outstanding guitar skills, Ped and Koong's shared attraction to her puts a strain on the band's survival, as well as their friendship.",
        backdrop = "/dAVnCgoJyamAOZhbLGowlDj0ZVx.jpg",
        poster = "/kgJ5VPugFupZnxQfzSPWuORm6nS.jpg",
        year = "2011",
        rating = 7.0,
        favorite = false
    )

    fun getOnlineDetailMovie(): MovieDetailResponse = MovieDetailResponse(
        id = 1,
        title = "Suckseed",
        overview = "\tPed (Jirayu La-ongmanee) was a shy boy who had never listened to music until introduced to the world of Pop and Rock by would-be childhood crush Ern (Nattasha Nualjam). Ern soon left for Bangkok, however, and it is six years later in their final school year when she is re-introduced to Ped and his brash best friend Koong (Pachara Chirathivat). Koong convinces Ped, along with schoolmate Ex (Thawat Pornrattanaprasert), to form a band, partly to be cool and attract girls, partly as an attempt by Koong to try and one-up his popular twin brother Kay. Their musical talents aren't great, but that doesn't stop them from trying. However, when Ern decides to lend them her outstanding guitar skills, Ped and Koong's shared attraction to her puts a strain on the band's survival, as well as their friendship.",
        backdrop = "/dAVnCgoJyamAOZhbLGowlDj0ZVx.jpg",
        poster = "/kgJ5VPugFupZnxQfzSPWuORm6nS.jpg",
        year = "2011",
        rating = 7.0
    )

    fun getDetailTvEntity(): TvEntity {
        return TvEntity(
            id = 1,
            name = "Kisah Untuk Geri",
            overview = "\tThis \"story for geri\" tells the story of a Garuda high school student titled Dinda and Geri. Dinda is known as the queen bee of The Satan gang and a child of a member of the DPR, Geri is a student who is quite famous, because of his mischief at school. are sworn enemies, the enmity began during the School Orientation Period. Dinda, who became the Primadona at her school, had a luxurious lifestyle, was always praised, the inversely proportional to her father being a suspect in a corruption case. Until finally her living conditions changed quickly, fell in an instant. All the attention she liked was lost. The only way to restore it all is to ask Geri to be his girlfriend so that he can be his protector and at the same time become a material for revenge against Jia, the enemy of Dinda. Then in the end Dinda understands that with Geri is injury and disaster for him and is the starting gate to the pain of heartbreak.",
            poster = "/mzQnuRZtjQNLEcCf8IKTjlFNePi.jpg",
            backdrop = "/mzQnuRZtjQNLEcCf8IKTjlFNePi.jpg",
            year = "2021",
            rating = 8.5,
            favorite = false
        )
    }


    fun getOnlineDetailTv(): TvDetailResponse = TvDetailResponse(
        id = 1,
        name = "Kisah Untuk Geri",
        overview = "\tThis \"story for geri\" tells the story of a Garuda high school student titled Dinda and Geri. Dinda is known as the queen bee of The Satan gang and a child of a member of the DPR, Geri is a student who is quite famous, because of his mischief at school. are sworn enemies, the enmity began during the School Orientation Period. Dinda, who became the Primadona at her school, had a luxurious lifestyle, was always praised, the inversely proportional to her father being a suspect in a corruption case. Until finally her living conditions changed quickly, fell in an instant. All the attention she liked was lost. The only way to restore it all is to ask Geri to be his girlfriend so that he can be his protector and at the same time become a material for revenge against Jia, the enemy of Dinda. Then in the end Dinda understands that with Geri is injury and disaster for him and is the starting gate to the pain of heartbreak.",
        poster = "/mzQnuRZtjQNLEcCf8IKTjlFNePi.jpg",
        backdrop = "/mzQnuRZtjQNLEcCf8IKTjlFNePi.jpg",
        year = "2021",
        rating = 8.5
    )
}