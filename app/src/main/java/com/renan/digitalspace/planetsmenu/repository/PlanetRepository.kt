package com.renan.digitalspace.planetsmenu.repository

import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet

class PlanetRepository {

    fun getPlanets(callback: (planets: List<Planet>) -> Unit) {
        val planetsDataSet = setPlanetsList()
        callback.invoke(planetsDataSet)
    }

    private fun setPlanetsList(): List<Planet> {
        return listOf(
            Planet(
                R.drawable.sol,
                "Sol",
                "Sol sol sol sol sol sol.",
                "•\tSol sol sol sol sol sol;\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tSol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol Sol.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Sol sol sol sol sol\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.mercurio,
                "Mercúrio",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tMercúrio mercúrio mercúrio mercúrio mercúrio mercúrio;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tmercúrio mercúrio mercúrio mercúrio mercúrio mercúrio mercúrio.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "mercúrio mercúrio mercúrio mercúrio mercúrio mercúrio\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.venus,
                "Vênus",
                "Vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus.",
                "•\tVênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus vênus\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.terra,
                "Terra",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.marte,
                "Marte",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.jupiter,
                "Júpiter",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.saturno,
                "Saturno",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.urano,
                "Urano",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide"),
            Planet(
                R.drawable.netuno,
                "Netuno",
                "Vênus é o segundo planeta mais próximo ao Sol e o segundo objeto mais brilhante no céu noturno, atrás apenas da lua. Devido às muitas semelhanças com nosso planeta, Vênus também é considerado um planeta irmão da Terra, por conta de sua massa densidade e volume, mas apesar disso, ele tem características únicas e impressionantes.",
                "•\tDiâmetro: 12,104 km;\n" +
                        "•\tMassa: 4,87 x 10 ^ 24 kg (81,5% do tamanho da Terra);\n" +
                        "•\tLuas: nenhuma;\n" +
                        "•\tDistância Órbita: 108.209.475 km;\n" +
                        "•\tPeríodo de órbita: 225 dias;\n" +
                        "•\tTemperatura de superfície: 462 ° C;\n" +
                        "•\tPrimeiro registro: século XVII a.C.\n" +
                        "•\t. Tem cerca de 800 milhões de anos\n",
                "•\tVênus é o planeta mais próximo da Terra.\n" +
                        "•\tA rotação de Vênus ocorre de leste para oeste, contrária a todos os planetas do Sistema Solar.\n" +
                        "•\tO planeta recebeu esse nome em homenagem à Vênus, a deusa romana da beleza e do amor.\n" +
                        "•\tVênus pode ser visto da Terra sem o auxílio de equipamentos.\n" +
                        "•\tÉ o planeta mais quente, apesar de não ser o mais próximo do Sol.\n" +
                        "•\tUm dia em Vênus dura mais que um ano no planeta\n" +
                        "•\tCientistas acreditam na possibilidade da existência de água\n" +
                        "•\tVênus tem a maior concentração de vulcões do Sistema Solar\n" +
                        "•\tEle poderia ser mais fácil de colonizar do que Marte\n",
                "Vênus é um planeta da Rússia (segundo os russos)\n" +
                        "Vida na Terra pode ter ido até Vênus 'de carona' em asteroide")
        )
    }
}