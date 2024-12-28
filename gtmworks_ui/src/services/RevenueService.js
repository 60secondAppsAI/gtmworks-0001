import http from "../http-common"; 

class RevenueService {
  getAllRevenues(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/revenue/revenues`, searchDTO);
  }

  get(revenueId) {
    return this.getRequest(`/revenue/${revenueId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/revenue?field=${matchData}`, null);
  }

  addRevenue(data) {
    return http.post("/revenue/addRevenue", data);
  }

  update(data) {
  	return http.post("/revenue/updateRevenue", data);
  }
  
  uploadImage(data,revenueId) {
  	return http.postForm("/revenue/uploadImage/"+revenueId, data);
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

export default new RevenueService();
