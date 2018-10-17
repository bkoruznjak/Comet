package hr.from.bkoruznjak.comet.newplayer.model

data class DateDTO(var day: Int,
                   var month: Int,
                   var year: Int) {

    fun toDateString() = "$day.$month.$year"
}