import http from "../http-common"; 

class TeamService {
  getAllTeams(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/team/teams`, searchDTO);
  }

  get(teamId) {
    return this.getRequest(`/team/${teamId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/team?field=${matchData}`, null);
  }

  addTeam(data) {
    return http.post("/team/addTeam", data);
  }

  update(data) {
  	return http.post("/team/updateTeam", data);
  }
  
  uploadImage(data,teamId) {
  	return http.postForm("/team/uploadImage/"+teamId, data);
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

export default new TeamService();
