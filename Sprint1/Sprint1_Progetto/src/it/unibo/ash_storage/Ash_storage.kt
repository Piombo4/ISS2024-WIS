/* Generated by AN DISI Unibo */ 
package it.unibo.ash_storage

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

class Ash_storage ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
					var ash_qty: Int = 0;
					var R: Int = 0;
					val MAX: Int = 4;
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
					 transition(edgeName="t031",targetState="removeAsh",cond=whenRequest("empty_ash"))
					transition(edgeName="t032",targetState="addAsh",cond=whenDispatch("deposit_ash"))
				}	 
				state("addAsh") { //this:State
					action { //it:State
						if( ash_qty<MAX 
						 ){	ash_qty++   
						CommUtils.outblack("$name Depositata la cenere di un RP!")
						}
						else
						 {CommUtils.outblack("$name Deposito pieno! ")
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t033",targetState="removeAsh",cond=whenRequest("empty_ash"))
					transition(edgeName="t034",targetState="addAsh",cond=whenDispatch("deposit_ash"))
				}	 
				state("removeAsh") { //this:State
					action { //it:State
						 R = ash_qty;  
						 ash_qty = 0;  
						answer("empty_ash", "ashes_taken", "ashes_taken($R)"   )  
						CommUtils.outblack("$name Prelevata $R cenere")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
