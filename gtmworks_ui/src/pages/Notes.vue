<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <note-table
            v-if="notes && notes.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:notes="notes"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-notes="getAllNotes"
             >

            </note-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import NoteTable from "@/components/NoteTable";
import NoteService from "../services/NoteService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    NoteTable,
  },
  data() {
    return {
      notes: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllNotes(sortBy='noteId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await NoteService.getAllNotes(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.notes.length) {
					this.notes = response.data.notes;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching notes:", error);
        }
        
      } catch (error) {
        console.error("Error fetching note details:", error);
      }
    },
  },
  mounted() {
    this.getAllNotes();
  },
  created() {
    this.$root.$on('searchQueryForNotesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllNotes();
    })
  }
};
</script>
<style></style>
