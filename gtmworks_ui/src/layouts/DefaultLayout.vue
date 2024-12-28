<template>
  <a-layout style="min-height: 100vh">
    <!-- Header -->
    <a-layout-header style="background: #fff; padding: 0">
      <app-header />
    </a-layout-header>

    <!-- Main layout with sider and content -->
    <a-layout>
      <!-- Collapsible Sider with Controlled Open Keys for Nested Menu -->
      <a-layout-sider width="200px" collapsible style="background: #fff">
        <a-menu
          mode="inline"
          :openKeys="openKeys"
          @openChange="onOpenChange"
          style="height: 100%; border-right: 0"
        >
<!--          <a-sub-menu key="sub1" title="Menu 1">
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
            <a-sub-menu key="sub1-1" title="Nested Menu">
              <a-menu-item key="3">Option 3</a-menu-item>
              <a-menu-item key="4">Option 4</a-menu-item>
            </a-sub-menu>
          </a-sub-menu>
          <a-sub-menu key="sub2" title="Menu 2">
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
          </a-sub-menu>
-->>

	 
			
				<a-menu-item>
					<router-link to="/accounts">
						<span class="label">Accounts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/contacts">
						<span class="label">Contacts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/opportunitys">
						<span class="label">Opportunitys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/leads">
						<span class="label">Leads</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/tasks">
						<span class="label">Tasks</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/users">
						<span class="label">Users</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/cases">
						<span class="label">Cases</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/notes">
						<span class="label">Notes</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/events">
						<span class="label">Events</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/campaigns">
						<span class="label">Campaigns</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/solutions">
						<span class="label">Solutions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/approvals">
						<span class="label">Approvals</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/revenues">
						<span class="label">Revenues</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/products">
						<span class="label">Products</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/inventorys">
						<span class="label">Inventorys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/contracts">
						<span class="label">Contracts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/assets">
						<span class="label">Assets</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/invoices">
						<span class="label">Invoices</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/payments">
						<span class="label">Payments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/teams">
						<span class="label">Teams</span>
					</router-link>
				</a-menu-item>
		
	
        </a-menu>
      </a-layout-sider>
      
      <!-- Content area -->
      <a-layout-content style="padding: 16px">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "DefaultLayout",
  components: {
    AppHeader,
  },
  setup() {
    const openKeys = ref(["sub1"]); // default open submenu
    const onOpenChange = (keys) => {
      // Open only the last clicked submenu, for single open behavior
      openKeys.value = keys.length ? [keys[keys.length - 1]] : [];
    };

    return {
      openKeys,
      onOpenChange,
    };
  },
};
</script>
<style>
.ant-layout {
    margin-top: 20px;
}
</style>

