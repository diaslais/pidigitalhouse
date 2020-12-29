package com.nasinha.digitalspace.quiz.model

object Constants {

        fun getQuestions(): ArrayList<QuestionsModel>{

        val questionsList = ArrayList<QuestionsModel>()

        val question1 = QuestionsModel(
            1,
            "Qual é a idade aproximada do universo?",
            "1.5 milhões de anos",
            "50 milhões de anos",
            "1.25 bilhões de anos",
            "13.8 bilhões de anos",
            4)

        questionsList.add(question1)

        val question2 = QuestionsModel(
            2,
            "Quantos planetas existem no Sistema Solar?",
            "6",
            "7",
            "8",
            "9",
            3)

        questionsList.add(question2)

        val question3 = QuestionsModel(
            3,
            "Qual é o maior planeta do Sistema Solar?",
            "Saturno",
            "Júpiter",
            "Terra",
            "Netuno",
            2)

        questionsList.add(question3)

        val question4 = QuestionsModel(
            4,
            "Qual é o menor planeta do Sistema Solar?",
            "Vênus",
            "Mercúrio",
            "Marte",
            "Terra",
            2)

        questionsList.add(question4)

        val question5 = QuestionsModel(
            5,
            "Qual é o planeta mais quente do Sistema Solar?",
            "Terra",
            "Marte",
            "Mercúrio",
            "Vênus",
            4)

        questionsList.add(question5)

        val question6 = QuestionsModel(
            6,
            "Quanto tempo a luz demora para chegar do Sol à Terra?",
            "Três minutos",
            "Cinco minutos",
            "8 minutos",
            "10 minutos",
            3)

        questionsList.add(question6)

        val question7 = QuestionsModel(
            7,
            "Os quatro planetas rochosos do nosso Sistema Solar são Mercúrio, Vênus, Terra e Marte. Mas seus núcleos são feitos principalmente do que?",
            "Carbono",
            "Ferro",
            "Chumbo",
            "Mercúrio",
            2)

        questionsList.add(question7)

        val question8 = QuestionsModel(
            8,
            "Qual é o nome da maior lua do Sistema Solar?",
            "Europa",
            "IO",
            "Ganimedes",
            "Calisto",
            3)

        questionsList.add(question8)

        val question9 = QuestionsModel(
            9,
            "Qual planeta do Sistema Solar tem o maior oceano?",
            "Júpiter",
            "Netuno",
            "Urano",
            "Terra",
            1)

        questionsList.add(question9)

        val question10 = QuestionsModel(
            10,
            "Quais são os nomes das luas de Marte?",
            "Arcas e Iasus",
            "Fobos e Deimos",
            "Pollux e Orion",
            "Tityos e Climene",
            4)

        questionsList.add(question10)
        return questionsList
    }
}