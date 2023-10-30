import java.text.SimpleDateFormat
import java.util.*

interface Bus{
    var brand : String  //бренд автобуса
    var year_of_manufacture : Date  //год выпуска
    var number_of_seats : Int  //количество мест
}

interface Route{
    var number : Int //номер маршрута
    var stations : List<String> //остановки
}

interface Bus_function{
    fun info()
}

interface Route_Bus_function{
    fun info()
}

interface Services{
    var air_con : Boolean //наличие кондиционера
    var toilet : Boolean //наличие туалета
    var trans_pet : Boolean //можно ли провозить животных
    var catering : Boolean //наличие питания
}

class City_Bus: Bus, Bus_function{
    override var brand : String = "default"
    override var year_of_manufacture : Date = SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01")
    override var number_of_seats : Int = 20

    var number_of_standing : Int = 5 //стоячие места

    constructor(_brand : String = "default",
                _year_of_manufacture : Date = SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"),
                _number_of_seats : Int = 20,
                _number_of_standing : Int = 5
    ){
        brand = _brand
        year_of_manufacture = _year_of_manufacture
        number_of_seats = _number_of_seats
        number_of_standing = _number_of_standing
    }

    constructor(_city_bus : City_Bus
    ):this(_city_bus.brand,
        _city_bus.year_of_manufacture,
        _city_bus.number_of_seats,
        _city_bus.number_of_standing
    )

    override fun info() {
        println("Городской автобус")
        println("Бренд: " + this.brand)
        println("Год выпуска: " + this.year_of_manufacture)
        println("Количество сидячих мест: " + this.number_of_seats)
        println("Количество стоячих мест: " + this.number_of_standing)
    }
}

class Intercity_Bus : Bus, Bus_function, Services{
    override var brand : String = "default"
    override var year_of_manufacture : Date = SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01")
    override var number_of_seats : Int = 100

    override var air_con : Boolean = false
    override var toilet : Boolean = false
    override var trans_pet : Boolean = false
    override var catering : Boolean = false

    constructor(_brand : String = "default",
                _year_of_manufacture : Date = SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"),
                _number_of_seats : Int = 20,
                _air_con : Boolean = false,
                _toilet : Boolean = false,
                _trans_pet : Boolean = false,
                _catering : Boolean = false
    ){
        brand = _brand
        year_of_manufacture = _year_of_manufacture
        number_of_seats = _number_of_seats
        air_con = _air_con
        toilet = _toilet
        trans_pet = _trans_pet
        catering = _catering
    }

    constructor(_intercity_bus : Intercity_Bus
    ):this(_intercity_bus.brand,
        _intercity_bus.year_of_manufacture,
        _intercity_bus.number_of_seats,
        _intercity_bus.air_con,
        _intercity_bus.toilet,
        _intercity_bus.trans_pet,
        _intercity_bus.catering
    )

    override fun info() {
        println("Городской автобус")
        println("Бренд: " + this.brand)
        println("Год выпуска: " + this.year_of_manufacture)
        println("Количество мест: " + this.number_of_seats)

        println("Наличие кондиционера: " + if(this.air_con) "есть" else "нет")
        println("Наличие туалета: " + if(this.toilet) "есть" else "нет")
        println("Возможность провоза животных: " + if(this.trans_pet) "есть" else "нет")
        println("Наличие питания: " + if(this.catering) "есть" else "нет")
    }
}

class City_Route_Buses: Route, Route_Bus_function{
    override var number : Int = 0
    override var stations : List<String> = listOf()

    var time_interval: Int = 5 //временной интервал между автобусами
    var buses : MutableList<City_Bus> = mutableListOf<City_Bus>() //автобусы данного маршрута

    constructor(_number : Int = 0,
                _time_interval: Int = 5,
                _stations : List<String> = listOf(),
                _buses : MutableList<City_Bus> = mutableListOf<City_Bus>()
    ){
        number = _number
        time_interval =_time_interval
        stations = _stations
        buses = _buses
    }

    constructor(_city_route_buses : City_Route_Buses
    ):this(_city_route_buses.number,
        _city_route_buses.time_interval,
        _city_route_buses.stations,
        _city_route_buses.buses
    )

    override fun info() {
        println("Маршрут автобуса №" + this.number)
        println("Интервал движения: " + this.time_interval)

        println("Список остановок: ")
        var i = 1
        for(st in this.stations)
        {
            print(st)
            if(i != this.stations.size)
                print(" - ")

            i += 1
        }
        println()
        println()

        println("Список автобусов: ")
        for(bus in this.buses)
        {
            bus.info()
            println()
        }
    }
}

class Intercity_Route_Buses: Route, Route_Bus_function{
    override var number : Int = 0
    override var stations : List<String> = listOf()
    var buses : MutableList<Intercity_Bus> = mutableListOf<Intercity_Bus>()

    constructor(_number : Int = 0,
                _stations : List<String> = listOf(),
                _buses : MutableList<Intercity_Bus> = mutableListOf<Intercity_Bus>()
    ){
        number = _number
        stations = _stations
        buses = _buses
    }

    constructor(_intercity_route_buses : Intercity_Route_Buses
    ):this(_intercity_route_buses.number,
        _intercity_route_buses.stations,
        _intercity_route_buses.buses
    )

    override fun info() {
        println("Маршрут автобуса №" + this.number)

        println("Список остановок: ")
        var i = 1
        for(st in this.stations)
        {
            print(st)
            if(i != this.stations.size)
                print(" - ")

            i += 1
        }
        println()
        println()

        println("Список автобусов: ")
        for(bus in this.buses)
        {
            bus.info()
            println()
        }
    }
}

class Bus_station{
    var intercity_buses : MutableList<Intercity_Route_Buses> = mutableListOf<Intercity_Route_Buses>()
    var city_buses : MutableList<City_Route_Buses> = mutableListOf<City_Route_Buses>()

    constructor(_intercity_buses : MutableList<Intercity_Route_Buses> = mutableListOf<Intercity_Route_Buses>(),
                _city_buses : MutableList<City_Route_Buses> = mutableListOf<City_Route_Buses>()
    ){
        intercity_buses = _intercity_buses
        city_buses = _city_buses
    }

    constructor(_bus_station : Bus_station
    ):this(_bus_station.intercity_buses,
        _bus_station.city_buses
    )

    fun info(){
        println("Список городских автобусов: ")
        for(city_bus in this.city_buses)
        {
            city_bus.info()
        }

        println("Список междугородних автобусов: ")
        for(intercity_bus in this.intercity_buses)
        {
            intercity_bus.info()
        }
    }
}

fun main() {
    val _city_bus1 = City_Bus()
    val _city_bus2 = City_Bus("GAZelle")
    val _city_bus3 = City_Bus("Kamaz", SimpleDateFormat("yyyy-MM-dd").parse("2006-03-01"), 45, 5)

    val _city_route_buses1 = City_Route_Buses(23,
        5,
        listOf("улица Зеленая","городская больница им.Печали","МЭИ"),
        mutableListOf(_city_bus1, _city_bus2)
    )

    val _city_route_buses2 = City_Route_Buses(43,
        10,
        listOf("аэропорт им.СиШарпа","улица Дипломная","проспект Хромова","завод Котлин и Джава"),
        mutableListOf(_city_bus3)
    )

    val _intercity_bus1 = Intercity_Bus()
    val _intercity_bus2 = Intercity_Bus("GAZelle", SimpleDateFormat("yyyy-MM-dd").parse("2010-11-01"))
    val _intercity_bus3 = Intercity_Bus("Kamaz", SimpleDateFormat("yyyy-MM-dd").parse("2015-10-01"), 120,true,false)
    val _intercity_bus4 = Intercity_Bus("GAZelle", SimpleDateFormat("yyyy-MM-dd").parse("2013-08-05"),120,true,true,true)

    val _intercity_route_buses1 = Intercity_Route_Buses(120,
        listOf("Москва","Орехово-Зуево","Владимир"),
        mutableListOf(_intercity_bus1)
    )

    val _intercity_route_buses2 = Intercity_Route_Buses(134,
        listOf("Город1","Город2","Город3","Город4"),
        mutableListOf(_intercity_bus2, _intercity_bus3)
    )

    val _intercity_route_buses3 = Intercity_Route_Buses(443,
        listOf("Москва","Рязань","Пенза","Саратов"),
        mutableListOf(_intercity_bus4)
    )

    val _bus_station = Bus_station(mutableListOf(_intercity_route_buses1,_intercity_route_buses2,_intercity_route_buses3),
        mutableListOf(_city_route_buses1,_city_route_buses2))

    _bus_station.info()
}
