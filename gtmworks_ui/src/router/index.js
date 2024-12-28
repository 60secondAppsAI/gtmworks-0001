import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Accounts from  '@/pages/Accounts.vue';
import AccountDetail from  '@/pages/AccountDetail.vue';
import Contacts from  '@/pages/Contacts.vue';
import ContactDetail from  '@/pages/ContactDetail.vue';
import Opportunitys from  '@/pages/Opportunitys.vue';
import OpportunityDetail from  '@/pages/OpportunityDetail.vue';
import Leads from  '@/pages/Leads.vue';
import LeadDetail from  '@/pages/LeadDetail.vue';
import Tasks from  '@/pages/Tasks.vue';
import TaskDetail from  '@/pages/TaskDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Cases from  '@/pages/Cases.vue';
import CaseDetail from  '@/pages/CaseDetail.vue';
import Notes from  '@/pages/Notes.vue';
import NoteDetail from  '@/pages/NoteDetail.vue';
import Events from  '@/pages/Events.vue';
import EventDetail from  '@/pages/EventDetail.vue';
import Campaigns from  '@/pages/Campaigns.vue';
import CampaignDetail from  '@/pages/CampaignDetail.vue';
import Solutions from  '@/pages/Solutions.vue';
import SolutionDetail from  '@/pages/SolutionDetail.vue';
import Approvals from  '@/pages/Approvals.vue';
import ApprovalDetail from  '@/pages/ApprovalDetail.vue';
import Revenues from  '@/pages/Revenues.vue';
import RevenueDetail from  '@/pages/RevenueDetail.vue';
import Products from  '@/pages/Products.vue';
import ProductDetail from  '@/pages/ProductDetail.vue';
import Inventorys from  '@/pages/Inventorys.vue';
import InventoryDetail from  '@/pages/InventoryDetail.vue';
import Contracts from  '@/pages/Contracts.vue';
import ContractDetail from  '@/pages/ContractDetail.vue';
import Assets from  '@/pages/Assets.vue';
import AssetDetail from  '@/pages/AssetDetail.vue';
import Invoices from  '@/pages/Invoices.vue';
import InvoiceDetail from  '@/pages/InvoiceDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Teams from  '@/pages/Teams.vue';
import TeamDetail from  '@/pages/TeamDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/accounts',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/accounts',
		name: 'Accounts',
		layout: DefaultLayout,
		component: Accounts,
	},
	{
	    path: '/account/:accountId', 
	    name: 'AccountDetail',
		layout: DefaultLayout,
	    component: AccountDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/contacts',
		name: 'Contacts',
		layout: DefaultLayout,
		component: Contacts,
	},
	{
	    path: '/contact/:contactId', 
	    name: 'ContactDetail',
		layout: DefaultLayout,
	    component: ContactDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/opportunitys',
		name: 'Opportunitys',
		layout: DefaultLayout,
		component: Opportunitys,
	},
	{
	    path: '/opportunity/:opportunityId', 
	    name: 'OpportunityDetail',
		layout: DefaultLayout,
	    component: OpportunityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/leads',
		name: 'Leads',
		layout: DefaultLayout,
		component: Leads,
	},
	{
	    path: '/lead/:leadId', 
	    name: 'LeadDetail',
		layout: DefaultLayout,
	    component: LeadDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tasks',
		name: 'Tasks',
		layout: DefaultLayout,
		component: Tasks,
	},
	{
	    path: '/task/:taskId', 
	    name: 'TaskDetail',
		layout: DefaultLayout,
	    component: TaskDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cases',
		name: 'Cases',
		layout: DefaultLayout,
		component: Cases,
	},
	{
	    path: '/case/:caseId', 
	    name: 'CaseDetail',
		layout: DefaultLayout,
	    component: CaseDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notes',
		name: 'Notes',
		layout: DefaultLayout,
		component: Notes,
	},
	{
	    path: '/note/:noteId', 
	    name: 'NoteDetail',
		layout: DefaultLayout,
	    component: NoteDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/events',
		name: 'Events',
		layout: DefaultLayout,
		component: Events,
	},
	{
	    path: '/event/:eventId', 
	    name: 'EventDetail',
		layout: DefaultLayout,
	    component: EventDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/campaigns',
		name: 'Campaigns',
		layout: DefaultLayout,
		component: Campaigns,
	},
	{
	    path: '/campaign/:campaignId', 
	    name: 'CampaignDetail',
		layout: DefaultLayout,
	    component: CampaignDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/solutions',
		name: 'Solutions',
		layout: DefaultLayout,
		component: Solutions,
	},
	{
	    path: '/solution/:solutionId', 
	    name: 'SolutionDetail',
		layout: DefaultLayout,
	    component: SolutionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/approvals',
		name: 'Approvals',
		layout: DefaultLayout,
		component: Approvals,
	},
	{
	    path: '/approval/:approvalId', 
	    name: 'ApprovalDetail',
		layout: DefaultLayout,
	    component: ApprovalDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/revenues',
		name: 'Revenues',
		layout: DefaultLayout,
		component: Revenues,
	},
	{
	    path: '/revenue/:revenueId', 
	    name: 'RevenueDetail',
		layout: DefaultLayout,
	    component: RevenueDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/products',
		name: 'Products',
		layout: DefaultLayout,
		component: Products,
	},
	{
	    path: '/product/:productId', 
	    name: 'ProductDetail',
		layout: DefaultLayout,
	    component: ProductDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/inventorys',
		name: 'Inventorys',
		layout: DefaultLayout,
		component: Inventorys,
	},
	{
	    path: '/inventory/:inventoryId', 
	    name: 'InventoryDetail',
		layout: DefaultLayout,
	    component: InventoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/contracts',
		name: 'Contracts',
		layout: DefaultLayout,
		component: Contracts,
	},
	{
	    path: '/contract/:contractId', 
	    name: 'ContractDetail',
		layout: DefaultLayout,
	    component: ContractDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/assets',
		name: 'Assets',
		layout: DefaultLayout,
		component: Assets,
	},
	{
	    path: '/asset/:assetId', 
	    name: 'AssetDetail',
		layout: DefaultLayout,
	    component: AssetDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/invoices',
		name: 'Invoices',
		layout: DefaultLayout,
		component: Invoices,
	},
	{
	    path: '/invoice/:invoiceId', 
	    name: 'InvoiceDetail',
		layout: DefaultLayout,
	    component: InvoiceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/teams',
		name: 'Teams',
		layout: DefaultLayout,
		component: Teams,
	},
	{
	    path: '/team/:teamId', 
	    name: 'TeamDetail',
		layout: DefaultLayout,
	    component: TeamDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
