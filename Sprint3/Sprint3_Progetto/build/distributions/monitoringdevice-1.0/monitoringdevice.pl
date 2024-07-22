%====================================================================================
% monitoringdevice description   
%====================================================================================
dispatch( led_status, led_status(X) ).
dispatch( ash_level, ash_level(LEVEL) ).
event( sonardata, distance(D) ).
dispatch( sonarwork, sonarwork(X) ).
dispatch( doread, doread(X) ).
%====================================================================================
context(ctx_monitoring_device, "localhost",  "TCP", "8021").
 qactor( led, ctx_monitoring_device, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctx_monitoring_device, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonardevice, ctx_monitoring_device, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
