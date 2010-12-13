package com.example

class WheelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [wheelInstanceList: Wheel.list(params), wheelInstanceTotal: Wheel.count()]
    }

    def create = {
        def wheelInstance = new Wheel()
        wheelInstance.properties = params
        return [wheelInstance: wheelInstance]
    }

    def save = {
        def wheelInstance = new Wheel(params)
        if (wheelInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'wheel.label', default: 'Wheel'), wheelInstance.id])}"
            redirect(action: "show", id: wheelInstance.id)
        }
        else {
            render(view: "create", model: [wheelInstance: wheelInstance])
        }
    }

    def show = {
        def wheelInstance = Wheel.get(params.id)
        if (!wheelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'wheel.label', default: 'Wheel'), params.id])}"
            redirect(action: "list")
        }
        else {
            [wheelInstance: wheelInstance]
        }
    }

    def edit = {
        def wheelInstance = Wheel.get(params.id)
        if (!wheelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'wheel.label', default: 'Wheel'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [wheelInstance: wheelInstance]
        }
    }

    def update = {
        def wheelInstance = Wheel.get(params.id)
        if (wheelInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (wheelInstance.version > version) {
                    
                    wheelInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'wheel.label', default: 'Wheel')] as Object[], "Another user has updated this Wheel while you were editing")
                    render(view: "edit", model: [wheelInstance: wheelInstance])
                    return
                }
            }
            wheelInstance.properties = params
            if (!wheelInstance.hasErrors() && wheelInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'wheel.label', default: 'Wheel'), wheelInstance.id])}"
                redirect(action: "show", id: wheelInstance.id)
            }
            else {
                render(view: "edit", model: [wheelInstance: wheelInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'wheel.label', default: 'Wheel'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def wheelInstance = Wheel.get(params.id)
        if (wheelInstance) {
            try {
                wheelInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'wheel.label', default: 'Wheel'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'wheel.label', default: 'Wheel'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'wheel.label', default: 'Wheel'), params.id])}"
            redirect(action: "list")
        }
    }
}
