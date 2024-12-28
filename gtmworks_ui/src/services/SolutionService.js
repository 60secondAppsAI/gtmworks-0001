import http from "../http-common"; 

class SolutionService {
  getAllSolutions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/solution/solutions`, searchDTO);
  }

  get(solutionId) {
    return this.getRequest(`/solution/${solutionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/solution?field=${matchData}`, null);
  }

  addSolution(data) {
    return http.post("/solution/addSolution", data);
  }

  update(data) {
  	return http.post("/solution/updateSolution", data);
  }
  
  uploadImage(data,solutionId) {
  	return http.postForm("/solution/uploadImage/"+solutionId, data);
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

export default new SolutionService();
