package com.revature.services;

import com.revature.beans.Batch;
import com.revature.beans.Trainer;
import com.revature.beans.Week;
import com.revature.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TableService {

    //load DAO
    private final StoreRetrieveService SRSserv;

    @Autowired
    public TableService(StoreRetrieveService srSserv) {
        SRSserv = srSserv;
    }

    /**
     * @param id
     * @return
     */
    public TechnicalStatusPerBatch technicalStatusPerBatchTable(int id) {
        Trainer trainer = SRSserv.getTrainerById(id);
        TechnicalStatusPerBatch table = new TechnicalStatusPerBatch();
        ArrayList<String> batchIds = new ArrayList<String>();

        ArrayList<String> batchName = new ArrayList<String>();
        ArrayList<int[]> techStatus = new ArrayList<int[]>();
        for (Batch b : trainer.getBatches()) {
            batchIds.add(b.getBatchId());
            batchName.add(b.getBatchName());
            int poor = 0;
            int good = 0;
            int average = 0;
            int superstar = 0;
            int n = 0;
            for (Week w : b.getWeeks()) {
                if (w.getTechnicalStatus() != null) {
                    if (w.getTechnicalStatus().contains("Good")) {
                        good++;
                    } else if (w.getTechnicalStatus().contains("Average")) {
                        average++;
                    } else if (w.getTechnicalStatus().contains("Poor")) {
                        poor++;
                    } else if (w.getTechnicalStatus().contains("Superstar")) {

                        superstar++;
                    } else if (w.getTechnicalStatus().contains("null")) {
                        n++;
                    }
                }// end if

            }// end week for loop
            int[] counts = {poor, average, good, superstar, n};
            techStatus.add(counts);
        } // end batch for loop

        table.setBatchId(batchIds);
        table.setBatchName(batchName);
        table.setTechnicalStatus(techStatus);
        return table;
    }// end of method

    /**
     * @param trainer_id
     * @return
     */
    public TechnicalStatusByWeek technicalStatusByWeekTable(int trainer_id) {
        return null;
    }

    /**
     * @param trainer_id
     * @return
     */
    public BatchTechnicalStatus batchTechnicalStatusTable(int trainer_id) {
        return null;
    }

    /**
     * @param trainer_id
     * @return
     */
    public AssessScoresByCategoryAllBatches assessScoresByCategoryAllBatchesTable(int trainer_id) {
        return null;
    }

    /**
     * @param trainer_id
     * @return
     */
    public AssessmentByCategory assessmentByCategoryTable(int trainer_id) {
        return null;
    }

    /**
     * @param trainer_id
     * @return
     */
    public AssessmentByBatch assessmentByBatchTable(int trainer_id) {
        return null;
    }
}