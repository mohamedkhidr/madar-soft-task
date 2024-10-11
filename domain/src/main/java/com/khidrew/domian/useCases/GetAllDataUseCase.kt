package com.khidrew.domian.useCases

import com.khidrew.domian.repos.AllDataRepository

class GetAllDataUseCase constructor(private val allDataRepository: AllDataRepository) {
    suspend operator fun invoke() = allDataRepository.getAllInfo()

}