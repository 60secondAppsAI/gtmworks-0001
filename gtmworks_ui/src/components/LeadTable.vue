
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Leads</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalLeads = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalLeads">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Lead</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="LeadId" type="text" placeholder="Enter LeadId" v-model="leadToAdd.leadId"></base-input>
  <base-input label="FirstName" type="text" placeholder="Enter FirstName" v-model="leadToAdd.firstName"></base-input>
  <base-input label="LastName" type="text" placeholder="Enter LastName" v-model="leadToAdd.lastName"></base-input>
  <base-input label="Email" type="text" placeholder="Enter Email" v-model="leadToAdd.email"></base-input>
  <base-input label="PhoneNumber" type="text" placeholder="Enter PhoneNumber" v-model="leadToAdd.phoneNumber"></base-input>
  <base-input label="LeadSource" type="text" placeholder="Enter LeadSource" v-model="leadToAdd.leadSource"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="leads" :row-key="record => record.LeadId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <LeadPictureView :leads="leads" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import LeadService from "../services/LeadService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import LeadPictureView from './LeadPictureView.vue';


const leadsColumns = [
  "leadId",
  "year",
  "date",
  "competitionId",
  "leadId"
]

export default {
  props: {
    leads: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    LeadPictureView
  },

  data() {
    return {
      modalLeads: false,
        isTableView: true,

      columns: [
        {
          title: 'Lead Id',
		dataIndex: 'leadId',
          visible: true,
          scopedSlots: { customRender: 'leadId' },
          sorter: true
          //sorter: (a, b) => a.leadId - b.leadId,
          //sorter: (a, b) => a.leadId.localeCompare(b.leadId),
        },
        {
          title: 'First Name',
		dataIndex: 'firstName',
          visible: true,
          scopedSlots: { customRender: 'firstName' },
          sorter: true
          //sorter: (a, b) => a.firstName - b.firstName,
          //sorter: (a, b) => a.firstName.localeCompare(b.firstName),
        },
        {
          title: 'Last Name',
		dataIndex: 'lastName',
          visible: true,
          scopedSlots: { customRender: 'lastName' },
          sorter: true
          //sorter: (a, b) => a.lastName - b.lastName,
          //sorter: (a, b) => a.lastName.localeCompare(b.lastName),
        },
        {
          title: 'Email',
		dataIndex: 'email',
          visible: true,
          scopedSlots: { customRender: 'email' },
          sorter: true
          //sorter: (a, b) => a.email - b.email,
          //sorter: (a, b) => a.email.localeCompare(b.email),
        },
        {
          title: 'Phone Number',
		dataIndex: 'phoneNumber',
          visible: true,
          scopedSlots: { customRender: 'phoneNumber' },
          sorter: true
          //sorter: (a, b) => a.phoneNumber - b.phoneNumber,
          //sorter: (a, b) => a.phoneNumber.localeCompare(b.phoneNumber),
        },
        {
          title: 'Lead Source',
		dataIndex: 'leadSource',
          visible: true,
          scopedSlots: { customRender: 'leadSource' },
          sorter: true
          //sorter: (a, b) => a.leadSource - b.leadSource,
          //sorter: (a, b) => a.leadSource.localeCompare(b.leadSource),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} leads`,
      },

      leads: [],
      leadToAdd: {},

      leadsTable: {
        columns: [...leadsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'leadId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderLeadsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let leadsTableData = [];
      for (let i = 0; i < this.leads.length; i++) {
        leadsTableData.push({
          id: i,
          leadId: this.leads[i].leadId,
          year: this.leads[i].year,
          date: this.leads[i].date,
          competitionId: this.leads[i].competitionId,
          leadId: this.leads[i].leadId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-leads',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToAccountDetail(id) {
      this.$router.push({ name: 'AccountDetail', params: { accountId: id.toString() }})
    },
    routingToContactDetail(id) {
      this.$router.push({ name: 'ContactDetail', params: { contactId: id.toString() }})
    },
    routingToOpportunityDetail(id) {
      this.$router.push({ name: 'OpportunityDetail', params: { opportunityId: id.toString() }})
    },
    routingToLeadDetail(id) {
      this.$router.push({ name: 'LeadDetail', params: { leadId: id.toString() }})
    },
    routingToTaskDetail(id) {
      this.$router.push({ name: 'TaskDetail', params: { taskId: id.toString() }})
    },
    routingToUserDetail(id) {
      this.$router.push({ name: 'UserDetail', params: { userId: id.toString() }})
    },
    routingToCaseDetail(id) {
      this.$router.push({ name: 'CaseDetail', params: { caseId: id.toString() }})
    },
    routingToNoteDetail(id) {
      this.$router.push({ name: 'NoteDetail', params: { noteId: id.toString() }})
    },
    routingToEventDetail(id) {
      this.$router.push({ name: 'EventDetail', params: { eventId: id.toString() }})
    },
    routingToCampaignDetail(id) {
      this.$router.push({ name: 'CampaignDetail', params: { campaignId: id.toString() }})
    },
    routingToSolutionDetail(id) {
      this.$router.push({ name: 'SolutionDetail', params: { solutionId: id.toString() }})
    },
    routingToApprovalDetail(id) {
      this.$router.push({ name: 'ApprovalDetail', params: { approvalId: id.toString() }})
    },
    routingToRevenueDetail(id) {
      this.$router.push({ name: 'RevenueDetail', params: { revenueId: id.toString() }})
    },
    routingToProductDetail(id) {
      this.$router.push({ name: 'ProductDetail', params: { productId: id.toString() }})
    },
    routingToInventoryDetail(id) {
      this.$router.push({ name: 'InventoryDetail', params: { inventoryId: id.toString() }})
    },
    routingToContractDetail(id) {
      this.$router.push({ name: 'ContractDetail', params: { contractId: id.toString() }})
    },
    routingToAssetDetail(id) {
      this.$router.push({ name: 'AssetDetail', params: { assetId: id.toString() }})
    },
    routingToInvoiceDetail(id) {
      this.$router.push({ name: 'InvoiceDetail', params: { invoiceId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToTeamDetail(id) {
      this.$router.push({ name: 'TeamDetail', params: { teamId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForLeadsChanged', this.searchQuery);
		//this.renderLeadsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalLeads = false;

      const currentDate = new Date().getTime();
      this.leadToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.leadToAdd);
      console.log(jsonData);
      
      const res = await LeadService.addLead(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderLeadsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
