%====================================================================================
% wis description   
%====================================================================================
dispatch( start_robot, start_robot(X) ).
dispatch( waiting, waiting(X) ).
dispatch( robot_info, robot_info(X,Y,POSITION,JOB) ).
dispatch( waste_in, waste_in(X) ).
dispatch( waste_qty, waste_qty(X) ).
dispatch( get_waste, get_waste(X) ).
dispatch( turn_on, turn_on(X) ).
dispatch( burn_in, burn_in(X) ).
dispatch( burn_start, burn_start(X) ).
dispatch( burn_end, burn_end(X) ).
dispatch( get_ash, get_ash(X) ).
dispatch( deposit_ash, deposit_ash(X) ).
request( empty_ash, empty_ash(X) ).
reply( ashes_taken, ashes_taken(LEVEL) ).  %%for empty_ash
request( moverobot, moverobot(TARGETX,TARGETY) ).
dispatch( setdirection, dir(D) ).
dispatch( setrobotstate, setpos(X,Y,D) ).
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
dispatch( disengage, disengage(ARG) ).
dispatch( led_status, led_status(X) ).
dispatch( ash_level, ash_level(LEVEL) ).
event( sonardata, distance(D) ).
dispatch( sonarwork, sonarwork(X) ).
dispatch( doread, doread(X) ).
dispatch( guidata, guidata(WASTE_QTY,ASH_LEVEL,ISBURNING,X,Y,POSITION,JOB) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_basic_robot, "127.0.0.1",  "TCP", "8020").
context(ctx_monitoring_device, "127.0.0.2",  "TCP", "8021").
 qactor( basicrobot, ctx_basic_robot, "external").
  qactor( sonar, ctx_monitoring_device, "external").
  qactor( led, ctx_monitoring_device, "external").
  qactor( mock, ctx_wis, "it.unibo.mock.Mock").
 static(mock).
  qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( op_robot, ctx_wis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( ash_storage, ctx_wis, "it.unibo.ash_storage.Ash_storage").
 static(ash_storage).
  qactor( waste_storage, ctx_wis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
tracing.
