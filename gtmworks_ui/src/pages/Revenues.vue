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
            <revenue-table
            v-if="revenues && revenues.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:revenues="revenues"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-revenues="getAllRevenues"
             >

            </revenue-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RevenueTable from "@/components/RevenueTable";
import RevenueService from "../services/RevenueService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RevenueTable,
  },
  data() {
    return {
      revenues: [],
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
    async getAllRevenues(sortBy='revenueId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RevenueService.getAllRevenues(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.revenues.length) {
					this.revenues = response.data.revenues;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching revenues:", error);
        }
        
      } catch (error) {
        console.error("Error fetching revenue details:", error);
      }
    },
  },
  mounted() {
    this.getAllRevenues();
  },
  created() {
    this.$root.$on('searchQueryForRevenuesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRevenues();
    })
  }
};
</script>
<style></style>
