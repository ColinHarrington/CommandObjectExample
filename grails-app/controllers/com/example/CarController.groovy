package com.example

class CarController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [carInstanceList: Car.list(params), carInstanceTotal: Car.count()]
    }

    def create = {
        def carInstance = new Car()
        carInstance.properties = params
        return [carInstance: carInstance]
    }

    def save = {
        def carInstance = new Car(params)
        if (carInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'car.label', default: 'Car'), carInstance.id])}"
            redirect(action: "show", id: carInstance.id)
        }
        else {
            render(view: "create", model: [carInstance: carInstance])
        }
    }

    def show = {
        def carInstance = Car.get(params.id)
        if (!carInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'car.label', default: 'Car'), params.id])}"
            redirect(action: "list")
        }
        else {
            [carInstance: carInstance]
        }
    }

    def edit = {
        def carInstance = Car.get(params.id)
        if (!carInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'car.label', default: 'Car'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [carInstance: carInstance]
        }
    }

    def update = {
        def carInstance = Car.get(params.id)
        if (carInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (carInstance.version > version) {
                    
                    carInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'car.label', default: 'Car')] as Object[], "Another user has updated this Car while you were editing")
                    render(view: "edit", model: [carInstance: carInstance])
                    return
                }
            }
            carInstance.properties = params
            if (!carInstance.hasErrors() && carInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'car.label', default: 'Car'), carInstance.id])}"
                redirect(action: "show", id: carInstance.id)
            }
            else {
                render(view: "edit", model: [carInstance: carInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'car.label', default: 'Car'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def carInstance = Car.get(params.id)
        if (carInstance) {
            try {
                carInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'car.label', default: 'Car'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'car.label', default: 'Car'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'car.label', default: 'Car'), params.id])}"
            redirect(action: "list")
        }
    }
}
