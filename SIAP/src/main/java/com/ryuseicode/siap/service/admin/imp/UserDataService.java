package com.ryuseicode.siap.service.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.admin.UserData;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paramoutput.admin.UserDataParamOutput;
import com.ryuseicode.siap.repository.admin.imp.UserDataRepository;
import com.ryuseicode.siap.service.admin.intf.IUserDataService;

@Service
public class UserDataService implements IUserDataService {
	/**
	 * userDataRepository
	 */
	@Autowired
	private UserDataRepository userDataRepository;
	/**
	 * bCryptPasswordEncoder
	 */
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * Default constructor
	 */
	public UserDataService()
	{
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}	
	/**
	 * @name Get
	 * {@summary Method to get a collection of user data}
	 * @return collection of user data
	 */
	public List<UserDataParamOutput> Get() {
		return this.userDataRepository.Get();
	}
	/**
	 * @name Save
	 * {@summary Method to save a userData in the database}
	 * @param userData
	 */
	//@Transactional
	public void Save(UserData userData) throws Exception {
		// Check if another user exist
		if(this.userDataRepository.GetByNickname(userData.getNickname()) != null)
			throw new ServiceException("Ya existe un usuario con el mismo nickname");
		// Encode password
		userData.setPassword(this.bCryptPasswordEncoder.encode(userData.getPassword()));
		userData.setActive(1);
		// Save user
		String rowId = this.userDataRepository.Save(userData);
		// get the user
		UserData auxUserData = this.userDataRepository.GetByRowId(rowId);
		// set id
		userData.setUserDataId(auxUserData.getUserDataId());
	}
	/**
	 * @name Update
	 * {@summary Method to update an userData in the database}
	 * @param userData
	 */
	public int Update(UserData userData) throws Exception {
		// Check if user exist
		if(this.userDataRepository.GetByUserDataId(userData.getUserDataId()) == null)
			throw new ServiceException("El usuario a actualizar no ha sido encontrado");
		// Get nickname
		UserDataParamOutput auxUserData = this.userDataRepository.GetByNickname(userData.getNickname());
		if(auxUserData != null) {
			// Check if is the same element
			if(auxUserData.getUserData().getUserDataId() != userData.getUserDataId())
				throw new ServiceException("Ya existe un usuario con el mismo nickname");
		}
		// update user
		return this.userDataRepository.Update(userData);
	}
	/**
	 * @name UpdatePassword
	 * {@summary Method to update password of users}
	 * @param userData
	 */
	public int UpdatePassword(UserData userData) throws Exception {
		// Check if user exist
		if(this.userDataRepository.GetByUserDataId(userData.getUserDataId()) == null)
			throw new ServiceException("El usuario a actualizar no ha sido encontrado");
		// Encode password
		userData.setPassword(this.bCryptPasswordEncoder.encode(userData.getPassword()));
		// update user
		return this.userDataRepository.UpdatePassword(userData);
	}
	/**
	 * @name Delete
	 * {@sumamry Method to delete an userData in the database}
	 * @param userDataId
	 */
	public int Delete(int userDataId) throws Exception {
		// Check if user exist
		//if(this.userDataRepository.GetByUserDataId(userDataId) == null)
		//	throw new ServiceException("El usuario a actualizar no ha sido encontrado");
		// update user
		return this.userDataRepository.Delete(userDataId);
	}
}
