import http from "../http-common"; 

class NoteService {
  getAllNotes(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/note/notes`, searchDTO);
  }

  get(noteId) {
    return this.getRequest(`/note/${noteId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/note?field=${matchData}`, null);
  }

  addNote(data) {
    return http.post("/note/addNote", data);
  }

  update(data) {
  	return http.post("/note/updateNote", data);
  }
  
  uploadImage(data,noteId) {
  	return http.postForm("/note/uploadImage/"+noteId, data);
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

export default new NoteService();
