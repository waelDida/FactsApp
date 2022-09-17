package com.wapp.factsapp.utils

import com.wapp.factsapp.R
import com.wapp.factsapp.models.Question

fun questionsList(): MutableList<Question>{
    val q1 = Question(
            1,
            "Oslo is the capital city of:",
            "Finland","Montenegro","Norway","Denmark",
            "Norway")
    val q2 = Question(
            2,
            "What is the highest mountain in the world ?",
            "Mount Kilimanjaro","Mount Everest","Makalu","Denali",
            "Mount Everest")
    val q3 = Question(
            3,
            "Paramaribo is the capital city of",
            "Honduras","The Bahamas","Suriname","Cambodia",
            "Suriname")
    val q4 = Question(
            4,
            "Which flag is shown in the picture?",
            "Tunisia","Algeria","Turkey","Morocco",
            "Tunisia", R.drawable.flag_tunisia)
    val q5 = Question(
            5,
            "This flag is of which country",
            "Brazil","Argentine","Spain","Mexico",
            "Brazil",R.drawable.flag_brazil)
    val q6 = Question(
            6,
            "What is the capital city of Bahrain?",
            "Baghdad","Manama","Abu Dhabi","Sana\'a",
            "Manama")
    val q7 = Question(
            7,
            "In wich sport did Andre Agassi compete?",
            "VolleyBall","Basketball","Tennis","Boxing",
            "Tennis")
    val q8 = Question(
            8,
            "By area, which continent is the largest in the world ?",
            "Africa","Europe","Australia","Asia",
            "Asia")
    val q9 = Question(
            9,
            "How many minutes are there in half an hour ?",
            "120","60","15","30",
            "30")
    val q10 = Question(
            10,
            "Which cartoon character says : \"what's up doctor?\"?",
            "Daffy Duck","Porky Pig","Bugs Bunny","Mickey Mouse",
            "Bugs Bunny")
    val q11 = Question(
            11,
            "Stanford is a private research university in:",
            "England","Germany","United States","Australia",
            "United States")

    val q12 = Question(
            12,
            "In which country is Place of versailles located",
            "England","Italy","France","Spain",
            "France")
    val q13 = Question(
            13,
            "Who song \"Imagine\"?",
            "Elvis Presley","Aretha Franklin", "Bob Dylan", "John Lennon",
            "John Lennon")
    val q14 = Question(
            14,
            "Which philosopher coined the phrase \"God is dead\"?",
            "Socrates","Friedrich Nietzsche","John Locke","Immanuel Kant",
            "Friedrich Nietzsche")
    val q15 = Question(
            11,
            "\"Oh my God, they killed Kenny!\" was typically shouted on which tv show",
            "South Park","The Flintstones","Daria","Drawn Together",
            "South Park")
    val q16 = Question(
            16,
            "With which musical instrument is Eric Clapton mostly associated with?  ",
            "Drums","Piano","Saxophone","Guitar",
            "Guitar")
    val q17 = Question(
            17,
            "What complete the saying \"______ is thicker than water\"?",
            "Blood","Liquid","Oil","Air",
            "Blood")
    val q18 = Question(
            18,
            "Who wrote the play \"Romeo and Juliet\"?",
            "Arthur Miller","Ira Gershwin","Tennessee Williams","William Shakespeare",
            "William Shakespeare")
    val q19 = Question(
            19,
            "\"The Star-Spangled Banner\" is the national anthem of:",
            "Australia","The United States","France","The United Kingdom",
            "The United States")
    val q20 = Question(
            20,
            "In which country is this landmark located?",
            "England","Germany","United States","Australia",
            "United States",R.drawable.landmark_washington)
    val q21 = Question(
            21,
            "Justin Bieber was born in which country?",
            "England","Canada","Australia","USA",
            "Canada")
    val q22 = Question(
            22,
            "In which country is this landmark located?",
            "France","England","Italy","Australia",
            "France",R.drawable.landmark_paris)

    val q23 = Question(
            23,
            "In which country is this landmark located?",
            "China","Canada","Japan","USA",
            "Japan",R.drawable.landmark_japan)

    val q24 = Question(
            24,
            "What country won the very first FIFA World Cup in 1930?",
            "Brazil","England","Uruguay","France",
            "Uruguay")

    val q25 = Question(
            25,
            "In what year was the first-ever Wimbledon Championship held?",
            "1886","1894","1900","1910",
            "1886")

    val q26 = Question(
            26,
            "What year was the first model of the iPhone released?",
            "2005","2006","2007","2008",
            "2007")

    val q27 = Question(
            27,
            "Who is often called the father of the computer?",
            "Alan Turing ","John von Neumann ","Fred Brooks","Charles Babbage",
            "Charles Babbage")

    val q28 = Question(
            28,
            "What part of the atom has no electric charge?",
            "Proton","Neutron","Electron","Gluon",
            "Neutron")

    val q29 = Question(
            29,
            "Which planet is the hottest in the solar system?",
            "Mars","Venus","Saturn","Jupiter",
            "Venus")

    val q30 = Question(
            30,
            "Which planet has the most gravity in the solar system?",
            "Earth","Neptune","Jupiter","Saturn",
            "Jupiter")

    val q31 = Question(
            31,
            "How many molecules of oxygen does ozone have?",
            "2","3","4","5",
            "3")

    val q32 = Question(
            32,
            "Which American state is the largest(by area)?",
            "Nevada","Texas","California","Alaska",
            "Alaska")

    val q33 = Question(
            33,
            "Which two countries share the longest international border?",
            "Brazil and Argentina","Russia and China","Canada and USA","Algeria and Mali",
            "Canada and USA")

    val q34 = Question(
            34,
            "What is the capital city of New Zealand?",
            "Canberra","Sydney","Wellington","Helsinki",
            "Wellington")

    val q35 = Question(
            35,
            "The world's longest river is:",
            "Yellow river","Nile","Amazon","Yangtze",
            "Nile")

    val q36 = Question(
            36,
            "Who was the first woman to win a Nobel Prize?",
            "Marie Curie","Rosalyn Yalow","Gabriela Mistral","Toni Morrison",
            "Marie Curie")

    val q37 = Question(
            37,
            "Which animal can be seen on the Porsche logo?",
            "Wolf","leopard","jaguar","Horse",
            "Horse")

    val q38 = Question(
            38,
            "What does BMW stand for (in English)?",
            "British Motor Works","Bavarian Motor Works","Big Motor Works","Badge Motor Works",
            "Bavarian Motor Works")

    val q39 = Question(
            39,
            "Which city in India would you find the Taj Mahal in?",
            "New Delhi","Agra","Mumbai","Kolkata",
            "Agra")

    val q40 = Question(
            40,
            "When was the company Nike founded?",
            "1970","1971","1972","1973",
            "1971")

    val q41 = Question(
            41,
            "\"Adventure of Sherlock Holmes\" was written by which writer?",
            "Agatha Christie","George Orwell","Arthur Conan Doyle","James Joyce",
            "Arthur Conan Doyle")

    val q42 = Question(
            42,
            "How many films did Sean Connery play James Bond in?",
            "4","5","6","7",
            "7")

    val q43 = Question(
            43,
            "How many Lord of the Rings films are there?",
            "1","2","3","4",
            "3")

    val q44 = Question(
            44,
            "Who played Jack Dawson in Titanic?",
            "Billy Zane","Victor Garber","Leonardo DiCaprio","Bill Paxton",
            "Leonardo DiCaprio")

    val q45 = Question(
            45,
            "Which TV show featured house Targaryen and Stark?",
            "Outlander","Spartacus","The Last Kingdom","Game of Thrones",
            "Game of Thrones")

    val q46 = Question(
            46,
            "Which actor appeared in \"Face Off\" and \"Ghost Rider\"?",
            "Nicholas Cage","Tom Cruise","John Travolta","Sylvester Stallone",
            "Nicholas Cage")

    val q47 = Question(
            47,
            "Which mammal has no vocal cords?",
            "Elephant","Giraffe","The cheetah","Hyenas",
            "Giraffe")

    val q48 = Question(
            48,
            "How many eyes does a bee have?",
            "2","3","4","5",
            "5")

    val q49 = Question(
            49,
            "How many hearts does an octopus have?",
            "1","2","3","4",
            "3")

    val q50 = Question(
            50,
            "In what year did Steve Jobs die?",
            "2009","2010","2011","2012",
            "2011")

    val q51 = Question(
            51,
            "In which year World War I begin?",
            "1912","1913","1914","1915",
            "1914")

    val q52 = Question(
            52,
            "In which country Adolph Hitler was born?",
            "Germany","Austria","Poland","Switzerland",
            "Austria")

    val q53 = Question(
            53,
            "John F. Kennedy was assassinated in which city?",
            "New York","Dallas","Chicago","Washington",
            "Dallas")

    val q54 = Question(
            54,
            "Thor was the son of which God?",
            "Odin","Frigg","Balder","Loki",
            "Odin")

    val q55 = Question(
            55,
            "The Egyptian God of the Sun is:",
            "Osiris","Isis","Amun-Ra","Horus",
            "Amun-Ra")

    val q56 = Question(
            56,
            "Which country invented tea?",
            "India","Nepal","Japan","China",
            "China")

    val q57 = Question(
            57,
            "In which country is this landmark located?",
            "Mexico","Canada","Australia","USA",
            "Australia",R.drawable.landmark_australia)

    val q58 = Question(
            58,
            "In which country is this landmark located?",
            "France","Italy","Greece","Germany",
            "Greece",R.drawable.landmark_greece)

    val q59 = Question(
            59,
            "In which country is this landmark located?",
            "Germany","Sweden","Norway","Russia",
            "Russia",R.drawable.landmark_russia)

    val q60 = Question(
            60,
            "In which country is this landmark located?",
            "Poland","Germany","Austria","Hungary",
            "Germany",R.drawable.landmark_germany)

    val q61 = Question(
            61,
            "Which flag is shown in the picture?",
            "Spain","Albania","Portugal","Porto Rico",
            "Portugal", R.drawable.flag_portugal)

    val q62 = Question(
            62,
            "Which flag is shown in the picture?",
            "India","Pakistan","Nepal","Afghanistan",
            "India", R.drawable.flag_india)

    val q63 = Question(
            63,
            "Which flag is shown in the picture?",
            "Poland","France","Switzerland","Denmark",
            "Denmark", R.drawable.flag_danmark)

    val q64 = Question(
            64,
            "Which flag is shown in the picture?",
            "Senegal","Nigeria","Cameroon","Bahrain",
            "Cameroon", R.drawable.flag_cameroon)

    val q65 = Question(
            65,
            "Which flag is shown in the picture?",
            "South Korea","Japan","China","North Korea",
            "Japan", R.drawable.flag_japan)

    val q66 = Question(
            66,
            "Which flag is shown in the picture?",
            "Niger","Pakistan","India","Cameroon",
            "Niger", R.drawable.flag_niger)

    val q67 = Question(
            67,
            "In which country is the Leaning Tower of Pisa located?",
            "Spain","Portugal","Italy","Argentina",
            "Italy")

    val q68 = Question(
            68,
            "Who sings \"Another Brick in The Wall\"?",
            "Pink Floyed","The Beatles","Jimi Hendrix","Led Zeppelin",
            "Pink Floyed")

    val q69 = Question(
            69,
            "The river Thames flows through:",
            "Paris","Berlin","London","Bern",
            "London")

    val q70 = Question(
            70,
            "What is the capital city of Colombia?",
            "Berlin","Port Moresby","Tashkent","Bogota",
            "Bogota")

    val q71 = Question(
            71,
            "What sort of artist uses chisels and hammers?",
            "Potter","Sculptor","Weaver","Painter",
            "Sculptor")

    val q72 = Question(
            72,
            "Which city is known as the Big Apple?",
            "New York","Los Angeles","Paris","London",
            "New York")

    val q73 = Question(
            73,
            "What form of communication was invented first?",
            "The internet","The telegram","The letter","The telephone",
            "The letter")

    val q74 = Question(
            74,
            "According to legend, what fell on Isaac Newton's head?",
            "Grape","Apple","Orange","Banana",
            "Apple")

    val q75 = Question(
            75,
            "What does the \"F\" stand for in FBI?",
            "Fail","Federal","Forever","Fun",
            "Federal")

    val q76 = Question(
            76,
            "The Prisoner's Dilemma is an aspect of which field?",
            "Criminology","Game theory","Anthropology","Sociology",
            "Game theory")

    val q77 = Question(
            77,
            "Who sings \"Born in the USA.\"?",
            "Paul Simon","Elvis Presley","Micheal Jackson","Bruce Springsteen",
            "Paul Simon")

    val q78 = Question(
            78,
            "What form of communication was invented first?",
            "The internet","The telegram","The letter","The telephone",
            "The letter")

    val q79 = Question(
            79,
            "On which TV show is there a character named Peter Griffin?",
            "Friends","South Park","Family Guy","30 Rock",
            "Family Guy")

    val q80 = Question(
            80,
            "What is the capital city of the Czech Republic?",
            "Reykjavik","Prague","Luanda","Bogota",
            "Prague")

    val q81 = Question(
            81,
            "Who sang \"Lithium\"?",
            "Nirvana","Alice in chains","Manic Street Preachers ","Blood, Sweat & Tears",
            "Nirvana")

    val q82 = Question(
            82,
            "Which type of bird shares its name with one of Batman's archenemies?",
            "Eagle","Penguin","Crow","Seagull",
            "Penguin")

    val q83 = Question(
            83,
            "Which continent is the most populated?",
            "Africa","Europe","North America","Asia",
            "Asia")

    val q84 = Question(
            84,
            "\"David\", the masterpiece of renaissance sculpture, was created by:",
            "Raphael","Leonardo da Vinci","Auguste Rodin","Michelangelo",
            "Michelangelo")

    val q85 = Question(
            85,
            "What is Poland's capital city?",
            "Monrovia","Yaounde","Warsaw","Freetown",
            "Warsaw")

    val q86 = Question(
            86,
            "Who sings \"Enter Sandman\"?",
            "Pantera","Joy Division","Metallica","Dream Theater",
            "Metallica")

    val q87 = Question(
            87,
            "In which sport does Tiger Woods compete ",
            "Basketball","Golf","Boxing","Tennis",
            "Golf")

    val q88 = Question(
            88,
            "Sumo wrestlers and geisha girls belong to which of these cultures?",
            "Brazilian","Swiss","African","Japanese",
            "Japanese")

    val q89 = Question(
            89,
            "Which of these is not part of the digestive system?",
            "Appendix","Lungs","Stomach","Colon",
            "Lungs")

    val q90 = Question(
            90,
            "Knocking down all ten bowling pins on the first shot is called:",
            "A turkey","A strike","A spare","A gutterball",
            "A strike")

    val q91 = Question(
            91,
            "What is the title of Pink Floyd's debut album?",
            "Ummagumma","Animals","The Piper at the Gate of Dawn","A Saucerful of Secrets",
            "The Piper at the Gate of Dawn")

    val q92 = Question(
            92,
            "Which flag is shown in the picture?",
            "Netherlands","France","Russia","Denmark",
            "Russia", R.drawable.flag_russia)

    val q93 = Question(
            93,
            "Which flag is shown in the picture?",
            "Singapore","Thailand","Indonesia","India",
            "Singapore", R.drawable.flag_singapore)

    val q94 = Question(
            94,
            "Which flag is shown in the picture?",
            "Switzerland","Austria","Poland","Germany",
            "Austria", R.drawable.flag_austria)

    val q95 = Question(
            95,
            "Which flag is shown in the picture?",
            "Cameroon","Senegal","Nigeria","Mali",
            "Nigeria", R.drawable.flag_nigeria)

    val q96 = Question(
            96,
            "Which TV station is associated with Britain?",
            "BBC","NBC","ABC","ARD",
            "BBC")

    val q97 = Question(
            97,
            "\"Por favor\" is the Spanish way of saying what polite word?",
            "Goodbye","Nice","Thank you","Please",
            "Please")

    val q98 = Question(
            98,
            "Who was the first man to walk on the moon?",
            "Alan Shepard","Neil Armstrong","Yuri Gagarin","Aleksei Leonov",
            "Neil Armstrong")

    val q99 = Question(
            99,
            "Who ruled Cuba for 49 years until 2008?",
            "Manuel Noriega","Fidel Castro","Hugo chavez","Vicente Fox",
            "Fidel Castro")

    val q100 = Question(
            100,
            "Who is the Roman god of war?",
            "Athena","Minerva","Mars","Ares",
            "Mars")

    val q101 = Question(
            101,
            "Biologically, to wich \"Class\" do birds belong?",
            "Amphibian","Aves","Reptile","Mammal",
            "Aves")

    val q102 = Question(
            102,
            "Renaissance artist Leonardo da Vinci was born in the Tuscan hill town of Vinci, in the city of:",
            "Pisa","Rome","Livorno","Florence",
            "Florence")

    val q103 = Question(
            103,
            "From wich movie is the quote,\"I'm going to make him an offer he can't refuse\"?",
            "The Godfather","E.T.","Taxi Driver","Gone with the Wind",
            "The Godfather")

    val q104 = Question(
            104,
            "Genghis Khan was a leader of which people?",
            "Celts","Shoguns","Khemer","Mongols",
            "Mongols")

    val q105 = Question(
            105,
            "In which country would you find the city of Cologne?",
            "Germany","Belgium","Luxembourg","France",
            "Germany")

    val q106 = Question(
            106,
            "Who is credited with the first powered flight?",
            "Johannes Gutenberg","Thomas Edison","The Wright Brothers","Joseph Woodland",
            "The Wright Brothers")

    val q107 = Question(
            107,
            "Who played Monica Geller on the TV show \"Friends\"?",
            "Jennifer Aniston","Lisa Kudrow","Courtney Cox","Tina Fey",
            "Courtney Cox")

    val q108 = Question(
            108,
            "The synoptic scale is used in the field of:",
            "Oceanography","Agrophysics","Ceramography","Meteorology",
            "Meteorology")

    val q109 = Question(
            109,
            "Who painted the \"Mona Lisa\"?",
            "Vincent van Gogh","Michelangelo","Pablo Picasso","Leonardo da Vinci",
            "Leonardo da Vinci")

    val q110 = Question(
            110,
            "Who wrote the play \"Hamlet\"?",
            "Tennessee Williams","James Lapine","William Shakespeare","Lewis Carroll",
            "William Shakespeare")

    val q111 = Question(
            111,
            "What is the only remaining wonder of the ancient world?",
            "Lighthouse of Alexandria","Colossus of Rhodes","Great Pyramid of Giza","Mausoleum of Halicarnassus",
            "Great Pyramid of Giza")

    val q112 = Question(
            112,
            "The study of animals is:",
            "Zoology","Toxicology","Pharmacology","Botany",
            "Zoology")

    val q113 = Question(
            113,
            "Which continent is Bolivia located on?",
            "Europe","South America","Africa","Asia",
            "South America")

    val q114 = Question(
            114,
            "What French Fauvist painter and sculptor lived from 1869 to 1954?",
            "Jackson Pollock","Roy Lichtenstein","Henri Matisse","Georgia O'Keeffe",
            "Henri Matisse")

    val q115 = Question(
            115,
            "What was the Penguin's birth name in \"Batman\"?",
            "Oswald Cobblepot","Peter Parker","Harvey Dent","Bruce Wayne",
            "Oswald Cobblepot")

    val q116 = Question(
            116,
            "Who composed the opera\"Carmen\"?",
            "Richard Wagner","Amadeus Mozart","George Bizet","Franz Liszt",
            "George Bizet")

    val q117 = Question(
            117,
            "Which animal is not a mammal?",
            "Sheep","Cow","Ostrich","Rabbit",
            "Ostrich")

    val q118 = Question(
            118,
            "Who wrote the novel \"The Catcher in the Rye\"?",
            "J. D. Salinger","Sinclair Lewis","Samuel Beckett","Vladimir Nabokov",
            "J. D. Salinger")

    val q119 = Question(
            119,
            "Neurology is the study of:",
            "Ants","The muscles","The kidneys","The nervous system",
            "The nervous system")

    val q120 = Question(
            120,
            "What is the capital city of Austria?",
            "Victoria","Chisinau","Kingston","Vienna",
            "Vienna")

    val q121 = Question(
            121,
            "What is the title of Mariah Carey's debut album?",
            "Rainbow","Music Box","Mariah Carey","Emotions",
            "Mariah Carey")

    val q122 = Question(
            122,
            "Which animated film has characters named Baghera, Baloo and Kaa?",
            "The Jungle Book","Aladdin","Kung Fu Panda","the Lion King",
            "The Jungle Book")

    val q123 = Question(
            123,
            "Who directed the movie \"Star Wars\"?",
            "George Lucas","Francis Ford Coppola","Martin Scorsese","Ridley Scott",
            "George Lucas")

    val q124 = Question(
            124,
            "What sport is played in the NFL?",
            "Basketball","Ice Hockey","American Football","Baseball",
            "American Football")

    val q125 = Question(
            125,
            "What is the largest living bird species in the world?",
            "Emu","Gannet","Ostrich","Hawk",
            "Ostrich")

    val q126 = Question(
            126,
            "Dave Grohl is the frontman of which band?",
            "Slipknot","Blink 182","Foo Fighters","Fleetwood mac",
            "Foo Fighters")

    val q127 = Question(
            127,
            "Gollum is a fictional character from which series of books?",
            "The Chronicles of Narnia","The Lord of the Rings","Alex Rider","harry Potter",
            "The Lord of the Rings")

    val q128 = Question(
            128,
            "The song \"Circle of Life\" is on the soundtrack of which movie?",
            "pretty Woman","The Lion King","Breakfast at Tiffany's","Titanic",
            "The Lion King")

    val q129 = Question(
            129,
            "Who is famous for his Theory of Relativity?",
            "Isaac Newton","Galileo galilei","Albert Einstein","Ernest Walton",
            "Albert Einstein")

    val q130 = Question(
            130,
            "The Russian Revolution took place in:",
            "1907","1897","1917","1927",
            "1917")

    val q131 = Question(
            131,
            "Who composed \"The Four Seasons\"?",
            "Johan Sebastian Bach","Claude Debussy","Antonio Vivaldi","Amadeus Mozart",
            "Antonio Vivaldi")

    val q132 = Question(
            132,
            "What planet in our Solar System has the most liquid water on its surface?",
            "Earth","Mars","Neptune","Jupiter",
            "Earth")

    val q133 = Question(
            133,
            "Which American president was caught up in the \"Watergate scandal\"?",
            "Richard Nixon","Jimmy Carter","Gerald Ford","Lyndon B. Johnson",
            "Richard Nixon")

    val q134 = Question(
            134,
            "Hanoi is the capital city of:",
            "Vietnam","Thailand","Singapore","Malaysia",
            "Vietnam")

    val q135 = Question(
            135,
            "Which animal is shown?",
            "cheetah","Puma","Jaguar","Leopard",
            "Leopard",R.drawable.animal_leopard)

    val q136 = Question(
            136,
            "In which city is this landmark located?",
            "New York City","Chicago","Boston","Los Angeles",
            "Los Angeles",R.drawable.landmark_hollywood)

    val q137 = Question(
            137,
            "Which flower is shown in the picture?",
            "Orchid","Lavendar","Petunia","Iris",
            "Lavendar",R.drawable.plant_lavender)

    val q138 = Question(
        138,
        "In which country is this landmark located?",
        "Poland","Spain","Italy","Greece",
        "Italy",R.drawable.landmark_rome)

    val q139 = Question(
        139,
        "In which continent is this landmark located?",
        "Asia","Africa","America","Europe",
        "Africa",R.drawable.landmark_africa)

    val q140 = Question(
        140,
        "In which country is this landmark located?",
        "Poland","Portugal","Spain","France",
        "Spain",R.drawable.landmark_madrid)

    val q141 = Question(
        141,
        "Which flag is shown in the picture?",
        "Japan","Indonesia","China","Nepal",
        "China", R.drawable.flag_china)

    val q142 = Question(
        142,
        "Which flag is shown in the picture?",
        "New Zealand","England","Australia","Portugal",
        "Australia", R.drawable.flag_australia)

    val q143 = Question(
        143,
        "Which flag is shown in the picture?",
        "Tunisia","Iraq","Yemen","Bahrain",
        "Bahrain", R.drawable.flag_bahrain)

    val q144 = Question(
        144,
        "Which flag is shown in the picture?",
        "Egypt","Algeria","Qatar","Libya",
        "Egypt", R.drawable.flag_egypt)

    val q145 = Question(
        145,
        "Which flag is shown in the picture?",
        "Colombia","Peru","Equador","Brazil",
        "Equador", R.drawable.flag_equador)

    val q146 = Question(
        146,
        "Which flag is shown in the picture?",
        "Pakistan","Iran","India","China",
        "Pakistan", R.drawable.flag_pakistan)

    val q147 = Question(
        147,
        "Which flag is shown in the picture?",
        "Senegal","South Africa","Morocco","Mali",
        "South Africa", R.drawable.flag_south_africa)

    val q148 = Question(
        148,
        "Which flag is shown in the picture?",
        "Qatar","United Arab Emirates","Syria","Algeria",
        "United Arab Emirates", R.drawable.flag_uae)

    val q149 = Question(
        149,
        "Which flag is shown in the picture?",
        "Uruguay","Argentina","Mexico","Bolivia",
        "Uruguay", R.drawable.flag_urguay)

    val q150 = Question(
        150,
        "Which flag is shown in the picture?",
        "France","Romania","Greece","Latvia",
        "Greece", R.drawable.flag_greece)

    val q151 = Question(
        151,
        "In which country is this landmark located?",
        "Egypt","Mexico","Thailand","Peru",
        "Mexico",R.drawable.landmark_mexico)

    val q152 = Question(
        152,
        "Which animal is shown?",
        "Mandrill","Rhesus macaque","Emperor tamarin","Golden monkey",
        "Mandrill",R.drawable.animal_mandrile)

    val q153 = Question(
        153,
        "Which flower is shown in the picture?",
        "Tulip","Carnation","Lily","Crocus",
        "Lily",R.drawable.plant_lily)

    val q154 = Question(
        154,
        "In which city is this landmark located?",
        "Washington","San Fransisco","Chicago","Los Angeles",
        "San Fransisco",R.drawable.landmark_san_fransisco)

    val q155 = Question(
        155,
        "In which country is this landmark located?",
        "Indonesia","Pakistan","India","Philippines",
        "India",R.drawable.landmark_india)

    val q156 = Question(
            156,
            "In which country is this landmark located?",
            "Uruguay","Argentina","Brazil","Mexico",
            "Brazil",R.drawable.landmark_rio)

    val q157 = Question(
            157,
            "In which country is this landmark located?",
            "England","France","Germany","Belgium",
            "England",R.drawable.landmark_big_ben)

    val q158 = Question(
            158,
            "Which flag is shown in the picture?",
            "Turkey","Tunisia","Algeria","Libya",
            "Algeria", R.drawable.flag_algeria)

    val q159 = Question(
            159,
            "Which flag is shown in the picture?",
            "Paraguay","Argentina","Venezuela","Peru",
            "Argentina", R.drawable.flag_argentina)

    val q160 = Question(
            160,
            "Which flag is shown in the picture?",
            "Angola","Senegal","Cameroon","South Africa",
            "Angola", R.drawable.flag_angola)

    val q161 = Question(
            161,
            "Which flag is shown in the picture?",
            "Italy","Belgium","Spain","France",
            "Belgium", R.drawable.flag_belgium)
    val q162 = Question(
            162,
            "Which flag is shown in the picture?",
            "Finland","Denmark","Sweden","Scotland",
            "Finland", R.drawable.flag_finland)

    val q163 = Question(
            163,
            "Which flag is shown in the picture?",
            "Japan","Indonesia","China","South Korea",
            "South Korea", R.drawable.flag_korea_south)

    val q164 = Question(
            164,
            "Which flag is shown in the picture?",
            "Chile","Peru","Mexico","Venezuela",
            "Chile", R.drawable.flag_chile)

    val q165 = Question(
            165,
            "In which country is this landmark located?",
            "India","Thailand","Pakistan","Costa Rica",
            "Thailand",R.drawable.landmark_thailand)

    val q166 = Question(
            166,
            "In which country is this landmark located?",
            "China","Japan","Cambodia","Iran",
            "Cambodia",R.drawable.landmark_cambodia)
    val q167 = Question(
            167,
            "In which country is this landmark located?",
            "Italy","France","Germany","Portugal",
            "France",R.drawable.landmark_france_2)

    val q168 = Question(
            168,
            "In which country is this landmark located?",
            "Italy","France","Spain","Belgium",
            "Italy",R.drawable.landmark_italy_2)

    val q169 = Question(
            169,
            "In which city is this landmark located?",
            "London","Paris","Berlin","Madrid",
            "London",R.drawable.landmark_london_2)

    val q170 = Question(
            170,
            "In which country is this landmark located?",
            "Canada","United States","Australia","Mexico",
            "United States",R.drawable.landmark_road66)

    val q171 = Question(
            171,
            "Which flag is shown in the picture?",
            "Turkey","Tunisia","Algeria","Libya",
            "Turkey", R.drawable.flag_turkey)

    val q172 = Question(
            172,
            "Which flag is shown in the picture?",
            "Belgium","Germany","Spain","Greece",
            "Germany", R.drawable.flag_germany)

    val q173 = Question(
            173,
            "Which flag is shown in the picture?",
            "Algeria","Tchad","Libya","Sudan",
            "Libya", R.drawable.flag_libya)

    val q174 = Question(
            174,
            "Which flag is shown in the picture?",
            "United Kingdom","United States","Brazil","Venezuela",
            "United Kingdom", R.drawable.flag_uk)

    val q175 = Question(
            175,
            "Which flag is shown in the picture?",
            "France","Canada","United States","Australia",
            "United States", R.drawable.flag_usa)

    val q176 = Question(
            176,
            "Which flag is shown in the picture?",
            "Senegal","Togo","Egypt","Tunisia",
            "Togo", R.drawable.flag_togo)

    val q177 = Question(
            177,
            "Which flag is shown in the picture?",
            "Syria","Egypt","UAE","Qatar",
            "Qatar", R.drawable.flag_qatar)

    val q178 = Question(
            178,
            "Which flag is shown in the picture?",
            "Indonesia","India","Malaysia","USA",
            "Malaysia", R.drawable.flag_malaysia)

    val q179 = Question(
            179,
            "Which flag is shown in the picture?",
            "Morocco","Libya","Saudi Arabia","Yemen",
            "Morocco", R.drawable.flag_morroco)

    val q180 = Question(
            180,
            "Which flag is shown in the picture?",
            "Iraq","Turkey","Lebanon","Peru",
            "Lebanon", R.drawable.flag_lebanon)



    return mutableListOf(
            q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,
            q11,q12,q13,q14,q15,q16,q17,q18,
            q19,q20,q21,q22,q23,q24,q25,q26,
            q27,q28,q29,q30,q31,q32,q33,q34,
            q35,q36,q37,q38,q39,q40,q41,q42,
            q43,q44, q45,q46,q47,q48,q49,q50,
            q51,q52,q53,q54,q55,q56,q57,q58,
            q59,q60,q61,q62,q63,q64,q65,q66,
            q67,q68,q69,q70,q71,q72,q73,q74,q75,
            q76,q77,q78,q79,q80,q81,q82,q83,
            q84,q85,q86,q87,q88,q89,q90,q91,
            q92,q93,q94,q95,q96,q97,q98,q99,
            q100,q101,q102,q103,q104,q105,q106,
            q107,q108, q109,q110,q111,q112,q113,
            q114,q115,q116,q117,q118,q119,q120,
            q121,q122,q123,q124,q125,q126,q127,
            q128,q129,q130,q131,q132,q133,q134,
            q135,q136,q137,q138,q139,q140,q141,q142,
            q143,q144,q145,q146,q147,q148,q149,q150,
            q151,q152,q153,q154,q155,q156,q157,
            q158,q159,q160,q161,q162,q163,q164,
            q165,q166,q167,q168,q169,q170,q171,q172,
            q173,q174,q175,q176,q177,q178,q179,q180)
}