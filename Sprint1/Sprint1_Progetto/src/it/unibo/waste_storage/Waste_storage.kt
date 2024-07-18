/* Generated by AN DISI Unibo */ 
package it.unibo.waste_storage

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Waste_storage ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
					var scale: Int = 0;
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(500) 
						CommUtils.outgreen("$name INIZIATO")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t033",targetState="addRP",cond=whenDispatch("waste_in"))
					transition(edgeName="t034",targetState="removeRP",cond=whenDispatch("get_waste"))
				}	 
				state("addRP") { //this:State
					action { //it:State
							scale += 50;   
						updateResourceRep( "waste_qty($scale)"  
						)
						CommUtils.outblack("$name Depositato un RP!")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t035",targetState="addRP",cond=whenDispatch("waste_in"))
					transition(edgeName="t036",targetState="removeRP",cond=whenDispatch("get_waste"))
				}	 
				state("removeRP") { //this:State
					action { //it:State
							scale -= 50   
						updateResourceRep( "scaleinfo($scale)"  
						)
						CommUtils.outblack("$name Prelevato un RP!")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
