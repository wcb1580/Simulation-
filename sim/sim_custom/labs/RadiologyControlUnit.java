package labs;

import hccm.controlunits.ControlUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hccm.activities.ProcessActivity;
import hccm.controlunits.ControlUnit;
import hccm.entities.ActiveEntity;

public class RadiologyControlUnit extends ControlUnit {
	public void OnStartWaitForCheckIn(List<ActiveEntity> ents, double simTime) {

		 ArrayList<ActiveEntity> idleReceps = this.getEntitiesInActivity("ReceptionalistEntity", "WaitForTaskReceptionalist", simTime);
		 ActivityStartCompare actSartComp = this.new ActivityStartCompare();

		 if (idleReceps.size() > 0) {
			 Collections.sort(idleReceps, actSartComp);

			 ActiveEntity patient = ents.get(0);
			 ActiveEntity receptionalist = idleReceps.get(0);

			 transitionTo("CheckIn", patient, receptionalist);
		 }
		}
	
	public void OnStartWaitForScan(List<ActiveEntity> ents, double simTime) {
		
		 ArrayList<ActiveEntity> idleCTs = this.getEntitiesInActivity("CTMachineEntity", "WaitForTaskCTMachine",
		simTime);
		 ActivityStartCompare actSartComp = this.new ActivityStartCompare();
		
		 if (idleCTs.size() > 0) {
		 Collections.sort(idleCTs, actSartComp);
		
		 ActiveEntity patient = ents.get(0);
		 ActiveEntity ct = idleCTs.get(0);
		
		 transitionTo("Scan", patient, ct);
		 }
		 }
	
	public void OnStartWaitForTaskReceptionalist(List<ActiveEntity> ents, double simTime) {
		
		 ArrayList<ActiveEntity> waitPats = this.getEntitiesInActivity("PatientEntity", "WaitForCheckIn", simTime);
		 ActivityStartCompare actSartComp = this.new ActivityStartCompare();
		
		
		 if (waitPats.size() > 0) {
		 Collections.sort(waitPats, actSartComp);
		
		 ActiveEntity patient = waitPats.get(0);
		 ActiveEntity receptionalist = ents.get(0);
		
		 transitionTo("CheckIn", patient, receptionalist);
		 }
		 }
	
	public void OnStartWaitForTaskCTMachine(List<ActiveEntity> ents, double simTime) {
		
		 ArrayList<ActiveEntity> waitPats = this.getEntitiesInActivity("PatientEntity", "WaitForScan", simTime);
		 ActivityStartCompare actSartComp = this.new ActivityStartCompare();
		
		 if (waitPats.size() > 0) {
		 Collections.sort(waitPats, actSartComp);
		
		 ActiveEntity patient = waitPats.get(0);
		 ActiveEntity ct = ents.get(0);
		
		 transitionTo("Scan", patient, ct);
		 }
		 }

}
