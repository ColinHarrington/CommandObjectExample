import com.example.Car
import com.example.Wheel

class BootStrap {

    def init = { servletContext ->
        Wheel w1 = new Wheel(size:"16")
        assert w1.save()
        Wheel w2 = new Wheel(size:"16")
        assert w2.save()
        Wheel w3 = new Wheel(size:"16")
        assert w3.save()
        Wheel w4 = new Wheel(size:"16")
        assert w4.save()
        Car car1 = new Car(name: "Chevy Volt", wheel: w1);
        assert car1.save()
    }
    def destroy = {
    }
}
