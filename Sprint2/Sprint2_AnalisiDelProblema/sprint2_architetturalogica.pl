%====================================================================================
% sprint2_architetturalogica description   
%====================================================================================
dispatch( start_robot, start_robot(X) ).
dispatch( waiting, waiting(X) ).
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
request( moverobot, moverobot(TARGETX,TARGETY) ).
dispatch( led_status, led_status(X) ).
event( sonardata, distance(D) ).
dispatch( sonarwork, sonarwork(X) ).
dispatch( doread, doread(X) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_basic_robot, "localhost",  "TCP", "8020").
context(ctx_monitoring_device, "localhost",  "TCP", "8021").
 qactor( basic_robot, ctx_basic_robot, "external").
  qactor( oprobot, ctx_wis, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( wastestorage, ctx_wis, "it.unibo.wastestorage.Wastestorage").
 static(wastestorage).
  qactor( ashstorage, ctx_wis, "it.unibo.ashstorage.Ashstorage").
 static(ashstorage).
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( led, ctx_monitoring_device, "it.unibo.led.Led").
 static(led).
  qactor( sonar24, ctx_monitoring_device, "it.unibo.sonar24.Sonar24").
 static(sonar24).
  qactor( sonardevice, ctx_monitoring_device, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
