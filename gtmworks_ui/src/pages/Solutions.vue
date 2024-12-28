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
            <solution-table
            v-if="solutions && solutions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:solutions="solutions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-solutions="getAllSolutions"
             >

            </solution-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SolutionTable from "@/components/SolutionTable";
import SolutionService from "../services/SolutionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SolutionTable,
  },
  data() {
    return {
      solutions: [],
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
    async getAllSolutions(sortBy='solutionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SolutionService.getAllSolutions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.solutions.length) {
					this.solutions = response.data.solutions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching solutions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching solution details:", error);
      }
    },
  },
  mounted() {
    this.getAllSolutions();
  },
  created() {
    this.$root.$on('searchQueryForSolutionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSolutions();
    })
  }
};
</script>
<style></style>
