package com.example

class MyController {
    def index = {
    }
    def changeWheel = { CarWheelCommand cmd ->
        if(cmd.wheel && cmd.car) {
            Car car = cmd.car
            car.wheel = cmd.wheel
            render car.save() ? 'OK' : 'ERROR'
        } else {
            render "Please enter a valid Car and wheel id to change"
        }
    }
}
class CarWheelCommand {
    Car car
    Wheel wheel
}
