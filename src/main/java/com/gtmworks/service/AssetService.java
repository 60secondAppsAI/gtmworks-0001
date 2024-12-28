package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Asset;
import com.gtmworks.dto.AssetDTO;
import com.gtmworks.dto.AssetSearchDTO;
import com.gtmworks.dto.AssetPageDTO;
import com.gtmworks.dto.AssetConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AssetService extends GenericService<Asset, Integer> {

	List<Asset> findAll();

	ResultDTO addAsset(AssetDTO assetDTO, RequestDTO requestDTO);

	ResultDTO updateAsset(AssetDTO assetDTO, RequestDTO requestDTO);

    Page<Asset> getAllAssets(Pageable pageable);

    Page<Asset> getAllAssets(Specification<Asset> spec, Pageable pageable);

	ResponseEntity<AssetPageDTO> getAssets(AssetSearchDTO assetSearchDTO);
	
	List<AssetDTO> convertAssetsToAssetDTOs(List<Asset> assets, AssetConvertCriteriaDTO convertCriteria);

	AssetDTO getAssetDTOById(Integer assetId);







}





