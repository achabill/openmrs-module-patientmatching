
package org.openmrs.module.patientmatching.db.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.patientmatching.db.PatientMatchingReportMetadataDao;
import org.openmrs.module.patientmatching.PatientMatchingReportMetadata;

public class HibernatePatientMatchingReportMetadataDAO implements PatientMatchingReportMetadataDao
{

  private SessionFactory sessionFactory;
  protected final Log log = LogFactory.getLog(this.getClass());
  
      public SessionFactory getSessionFactory()
      {
    	 return sessionFactory;
      }
      public void setSessionFactory(SessionFactory sessionFactory)
      {
    	  this.sessionFactory=sessionFactory;
    	  
      }
      
      public HibernatePatientMatchingReportMetadataDAO() {
  		super();
  		// TODO Auto-generated constructor stub
  	}

     public void saveReportDetails(PatientMatchingReportMetadata pri)  throws DAOException
    {    sessionFactory.getCurrentSession().beginTransaction();
    	Connection connection = sessionFactory.getCurrentSession().connection();
 		  PreparedStatement ps = null;
 		  
 		 try {
				ps = connection.prepareStatement("INSERT INTO  metadata_report (report_name,strategies_used,process_name_time,createdby,datecreated) VALUES (?, ?, ?, ?,?)");
				ps.setString(1,pri.getReportName());
				ps.setString(2,pri.getSelstrategies());
				ps.setString(3,pri.getpNameTime());
				ps.setString(4,pri.getCreatedBy());
				ps.setString(5,pri.getDateCreated());

                int i=ps.executeUpdate();
               

              
			}
			catch (SQLException e) {
				log.error("Error while inserting", e);
			}
			if (ps != null) {
				try {
					ps.close();
				}
				catch (SQLException e) {
					log.error("Error generated while closing", e);
				}
			}
    	
		/*
			public void showReportDetails(String reportName) throws DAOException
			{
				Connection connection = sessionFactory.getCurrentSession().connection();
				PreparedStatement ps = null;
				try {
					ps = connection.prepareStatement("SELECT * FROM metadata_report WHERE report_name =?");
					ps.setString(1,reportName);
					ps.execute();
					catch (SQLException e) {
						log.error("Error while trying to see if this role is already created", e);
					}
					if (ps != null) {
						try {
							ps.close();
						}
						catch (SQLException e) {
							log.error("Error generated while closing", e);
						}
					}
			}*/

	
    }
    
	
}
