package com.mycaretaker.service;

import java.time.LocalDateTime;
import java.util.List; 
import org.springframework.stereotype.Service;
import com.mycaretaker.entity.CareRequest; 
import com.mycaretaker.entity.Caretaker; 
import com.mycaretaker.entity.Elderly; 
import com.mycaretaker.entity.FamilyMember; 
import com.mycaretaker.repository.CareRequestRepository; 
import com.mycaretaker.repository.CaretakerRepository; 
import com.mycaretaker.repository.ElderlyRepository; 
import com.mycaretaker.repository.FamilyMemberRepository; 

@Service public class CareRequestService 
{
	private final CareRequestRepository careRequestRepository; 
	private final ElderlyRepository elderlyRepository; 
	private final CaretakerRepository caretakerRepository; 
	private final FamilyMemberRepository familyMemberRepository; 
	private final NotificationService notificationService;
	
	public CareRequestService( 
			CareRequestRepository careRequestRepository, 
			ElderlyRepository elderlyRepository,
			CaretakerRepository caretakerRepository,
			FamilyMemberRepository familyMemberRepository, 
			NotificationService notificationService) 
	{
		
		this.careRequestRepository = careRequestRepository; 
		this.elderlyRepository = elderlyRepository; 
		this.caretakerRepository = caretakerRepository; 
		this.familyMemberRepository = familyMemberRepository; 
		this.notificationService = notificationService; 
		
	}
	// ========================================================= 
	// ELDERLY -> CARETAKER //
	//=========================================================
	
	public CareRequest createCareRequest( 
			int elderlyId,
			int caretakerId, 
			String message) 
	{
		
		Elderly elderly = elderlyRepository
				.findById(elderlyId) .orElseThrow(() -> new RuntimeException( 
						"Elderly person not found"));
		Caretaker caretaker = caretakerRepository 
				.findById(caretakerId)
				.orElseThrow(() -> new RuntimeException( "Caretaker not found"));
		
		if (!"VERIFIED".equalsIgnoreCase( caretaker.getVerificationStatus()))
		{
			throw new RuntimeException( "Caretaker is not verified"); 
			
		}
		
		CareRequest careRequest = new CareRequest(); 
		
		careRequest.setElderly(elderly); 
		careRequest.setCaretaker(caretaker);
		careRequest.setMessage(message);
		careRequest.setStatus("PENDING"); 
		careRequest.setRequestDate(LocalDateTime.now()); 
		CareRequest savedCareRequest = careRequestRepository.save(careRequest);
		// Notify caretaker 
		
		notificationService.createNotification( 
				caretaker.getUser().getUserId(), 
				"You have received a new care request from "
				+ elderly.getUser().getFirstName() + " " + 
						elderly.getUser().getLastName() 
						);
		return savedCareRequest;
		
	} 
	
	// ========================================================
 // FAMILY MEMBER -> CARETAKER
	// ========================================================= 
	
	public CareRequest createFamilyMemberCareRequest(
			int familyMemberId, int caretakerId, String message) 
	{
		
		FamilyMember familyMember = familyMemberRepository
				.findById(familyMemberId)
				.orElseThrow(() -> new RuntimeException( "Family member not found")); 
		
		if (familyMember.getElderly() == null) 
		{
			
			throw new RuntimeException( "No elderly person connected to family member");
			
			
		}
		
		Elderly elderly = familyMember.getElderly();
		Caretaker caretaker = caretakerRepository 
				.findById(caretakerId)
				.orElseThrow(() -> new RuntimeException( "Caretaker not found"));
		
		if (!"VERIFIED".equalsIgnoreCase(
				caretaker.getVerificationStatus())) 
		{
			throw new RuntimeException( "Caretaker is not verified");
			
		}
		
		CareRequest careRequest = new CareRequest(); 
		careRequest.setElderly(elderly); 
		careRequest.setCaretaker(caretaker);
		careRequest.setFamilyMember(familyMember);
		careRequest.setMessage(message); 
		careRequest.setStatus("PENDING"); 
		
		// Important: request date must not be null
		
		careRequest.setRequestDate( LocalDateTime.now());
		CareRequest savedCareRequest = careRequestRepository.save(careRequest); 
		// Notify caretaker 
		
		notificationService.createNotification( 
				caretaker.getUser().getUserId(), 
				"You have received a new care request from family member "
				+ "" + familyMember.getUser().getFirstName() + " " + familyMember.getUser().getLastName() 
				+ " for elderly person " + elderly.getUser().getFirstName() + " " + 
				elderly.getUser().getLastName()
				
				);
		
		// Notify family member
		
		notificationService.createNotification( 
				familyMember.getUser().getUserId(), 
				"Your care request has been sent to " + 
				caretaker.getUser().getFirstName() + " " + 
						caretaker.getUser().getLastName() + 
						" for elderly person " + elderly.getUser().getFirstName() 
						+ " " + elderly.getUser().getLastName() 
						);
		return savedCareRequest;
		
	}
	// =========================================================
	// GET REQUESTS FOR ELDERLY
	// ========================================================= 
	
	public List<CareRequest> getCareRequestsForElderly( int elderlyId) 
	{
		return careRequestRepository 
				.findByElderlyElderlyId(elderlyId);
		
	}
	
	// =========================================================
	// GET REQUESTS FOR CARETAKER
	// ========================================================= 
	
	public List<CareRequest> getCareRequestsForCaretaker( int caretakerId)
	{
		return careRequestRepository 
				.findByCaretakerCaretakerId( caretakerId);
		
	}
	
	// ========================================================= 
	
	// GET PENDING REQUESTS FOR CARETAKER
	// ========================================================= 
	
	public List<CareRequest> getPendingCareRequestsForCaretaker( 
			int caretakerId)
	{
		return careRequestRepository 
				.findByCaretakerCaretakerIdAndStatus( 
						caretakerId, "PENDING");
		
	}
	
	// =========================================================
	// GET REQUESTS FOR FAMILY MEMBER 
	// ========================================================= 
	
	public List<CareRequest> getCareRequestsForFamilyMember( int familyMemberId)
	{
		return careRequestRepository 
				.findByFamilyMemberFamilyMemberId( familyMemberId); 
		
	}
	
	// ========================================================= 
	// GET CARE REQUEST BY ID 
	// ========================================================= 
	public CareRequest getCareRequestById( 
			int careRequestId) 
	
	{
		
		return careRequestRepository 
				.findById(careRequestId) .orElseThrow(() ->
				new RuntimeException( "Care request not found"));
		
	}
	
	// =========================================================
	// UPDATE CARE REQUEST STATUS
	// ========================================================= 
	
	public CareRequest updateCareRequestStatus( int careRequestId, String status) 
	{
		CareRequest careRequest = careRequestRepository
				.findById(careRequestId) .orElseThrow(() ->
				new RuntimeException( "Care request not found"));
		
		if (!"ACCEPTED".equalsIgnoreCase(status) && !"REJECTED".equalsIgnoreCase(status))
		
		{
			
			throw new RuntimeException( "Invalid care request status"); 
			
		}
		
		careRequest.setStatus( status.toUpperCase());
		CareRequest updatedCareRequest = careRequestRepository.save( careRequest);
		Caretaker caretaker = careRequest.getCaretaker(); 
		
		// =====================================================
		// Notify Elderly
		// ===================================================== 
		
		if ("ACCEPTED".equalsIgnoreCase(status)) 
		{ notificationService.createNotification( 
				careRequest .getElderly() .getUser() 
				.getUserId(), "Your care request has been accepted by " + 
				caretaker.getUser().getFirstName() + " " +
						caretaker.getUser().getLastName() );
		
		}
		
		else { notificationService.createNotification( careRequest
				.getElderly() .getUser() .getUserId(), 
				"Your care request has been rejected by " + 
				caretaker.getUser().getFirstName() + " " + 
						caretaker.getUser().getLastName()
						
				);
		
		}
		
		// ===================================================== 
		// Notify Family Member
		// ===================================================== 
		
		if (careRequest.getFamilyMember() != null)
		
		{
			String familyMessage;
			if ("ACCEPTED".equalsIgnoreCase(status))
			{
				familyMessage = "Your care request has been accepted by"
						+ " " + caretaker.getUser().getFirstName() + " " + 
						caretaker.getUser().getLastName();
				
			} else { 
				
				familyMessage = "Your care request has been rejected by"
						+ " " + caretaker.getUser().getFirstName() + " " + 
						caretaker.getUser().getLastName();
				}
			
			notificationService.createNotification( 
					careRequest .getFamilyMember() 
					.getUser() .getUserId(), 
					familyMessage );
			}
		
		return updatedCareRequest; 
		}
	


}