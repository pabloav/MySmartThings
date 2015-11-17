/**
 *  Battery Alerts
 *
 *  Author: pablo@vanitude.com
 *  Date: 2014-01-14
 */
preferences {
	section("Check battery status on these devices") {
    		input "devices", "capability.battery", multiple: true, required: true
	}
    
	section("Battery threshold") {
		input "batteryThreshold", "decimal", title: "Battery threshold", required: false
	}

}

def installed() {
	log.debug "Installed with settings: ${settings}"
	log.debug "Current mode = ${location.mode}, people = ${devices.collect{it.label + ': ' + it.battery}}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

    unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
    log.debug "Unsubscring from all devices"
//	unsubscribe()
    log.debug "Subscribing to selected devices"
	subscribe(devices, "battery", battery)

}

// TODO: implement event handlers

def battery(evt) {
	log.debug "evt.name: $evt.name - evt.value: $evt.value"
}