import http from "../http-common"; 

class ApprovalService {
  getAllApprovals(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/approval/approvals`, searchDTO);
  }

  get(approvalId) {
    return this.getRequest(`/approval/${approvalId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/approval?field=${matchData}`, null);
  }

  addApproval(data) {
    return http.post("/approval/addApproval", data);
  }

  update(data) {
  	return http.post("/approval/updateApproval", data);
  }
  
  uploadImage(data,approvalId) {
  	return http.postForm("/approval/uploadImage/"+approvalId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ApprovalService();
