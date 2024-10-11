package com.khidrew.domian.useCases

import com.khidrew.domian.entities.InfoModel
import com.khidrew.domian.repos.NewDataRepository

class AddNewDataUseCase constructor(private val newDataRepository: NewDataRepository) {
    suspend operator fun invoke(info: InfoModel) = newDataRepository.addNewData(info)

}