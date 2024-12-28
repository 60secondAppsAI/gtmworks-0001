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
            <approval-table
            v-if="approvals && approvals.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:approvals="approvals"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-approvals="getAllApprovals"
             >

            </approval-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ApprovalTable from "@/components/ApprovalTable";
import ApprovalService from "../services/ApprovalService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ApprovalTable,
  },
  data() {
    return {
      approvals: [],
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
    async getAllApprovals(sortBy='approvalId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ApprovalService.getAllApprovals(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.approvals.length) {
					this.approvals = response.data.approvals;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching approvals:", error);
        }
        
      } catch (error) {
        console.error("Error fetching approval details:", error);
      }
    },
  },
  mounted() {
    this.getAllApprovals();
  },
  created() {
    this.$root.$on('searchQueryForApprovalsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllApprovals();
    })
  }
};
</script>
<style></style>
