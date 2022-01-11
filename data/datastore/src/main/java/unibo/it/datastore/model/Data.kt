package unibo.it.datastore.model

open class Data(vararg data: Any) {
    fun getValue(key: String): Any {
        return this::class.members.map {
            if (it.name == key) {
                return@map it.call(this)
            }
            return@map null
        }.filterNotNull()[0]
    }
}