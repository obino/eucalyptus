/*************************************************************************
 * Copyright 2009-2012 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 ************************************************************************/

(function($, eucalyptus) {
  $.widget('eucalyptus.launchconfig', $.eucalyptus.eucawidget, {
    options : { },
    baseTable : null,
    tableWrapper : null,
    releaseDialog : null,
    allocateDialog : null,
    associateDialog : null,
    disassociateDialog : null,
    associateBtnId : 'launchconfig-associate-btn',

    _init : function() {
      var thisObj = this;
      var $tmpl = $('html body').find('.templates #launchConfigTblTmpl').clone();
      var $wrapper = $($tmpl.render($.extend($.i18n.map, help_scaling)));
      var $launchConfigTable = $wrapper.children().first();
      var $launchConfigHelp = $wrapper.children().last();
      this.baseTable = $launchConfigTable;
      this.tableWrapper = $launchConfigTable.eucatable({
        id : 'launchconfig', // user of this widget should customize these options,
        data_deps: ['launchconfigs' ],
        hidden: thisObj.options['hidden'],
        dt_arg : {
          "sAjaxSource": 'launchconfig',
          "aaSorting": [[ 1, "desc" ]],
          "aoColumnDefs": [
            {
              "aTargets" : [0],
              "bSortable": false,
              "mData": function(oObj) { return '<input type="checkbox"/>' },
              "sClass": "checkbox-cell"
            },
            {
              "aTargets" : [1],
              "mData": "name",
            },
            {
              "aTargets" : [2],
              "mData": "image_id",
            },
            {
              "aTargets" : [3],
              "mData": "key_name",
            },
            {
              "aTargets" : [4],
              "mData": "security_groups",
            },
            {
              "aTargets" : [5],
              "mData": "created_time",
            },
          ],
        },
        text : {
          header_title : launchconfig_h_title,
          create_resource : launchconfig_create,
          resource_found : 'launchconfig_found',
          resource_search : launchconfig_search,
          resource_plural : launchconfig_plural,
        },
        menu_actions : function(args){ 
          return thisObj._createMenuActions();
        },
        context_menu_actions : function(row) {
          return thisObj._createMenuActions();
        },
        menu_click_create : function (args) { thisObj._createAction() },
        help_click : function(evt) {
          thisObj._flipToHelp(evt, {content: $launchConfigHelp, url: help_launchconfig.landing_content_url});
        }
      });
      this.tableWrapper.appendTo(this.element);
      $(this.element).prepend($('#scaling-topselector', $wrapper));
    },

    _create : function() { 
      var thisObj = this;
    },

    _createAction : function() {
      window.location = '#newlaunchconfig';
    },

    _destroy : function() {
    },

    _createMenuActions : function() {
      var thisObj = this;
      selectedLaunchConfig = thisObj.baseTable.eucatable('getSelectedRows', 1);
      var itemsList = {};

      (function(){
        itemsList['create'] = { "name": launchconfig_action_create, callback: function(key, opt) {;}, disabled: function(){ return true;} }
        itemsList['delete'] = { "name": launchconfig_action_delete, callback: function(key, opt) {;}, disabled: function(){ return true;} }
      })();

      if ( selectedLaunchConfig.length === 1) {
        itemsList['create'] = {"name":launchconfig_action_create, callback: function(key, opt){ thisObj._dialogAction('createscalinggroupfromlaunchconfig', selectedLaunchConfig); }}
        itemsList['delete'] = {"name":launchconfig_action_delete, callback: function(key, opt){ thisObj._dialogAction('deletelaunchconfig', selectedLaunchConfig); }}
      }

      return itemsList;
    },

    _dialogAction: function(dialog, selectedLaunchConfig) {
        console.log('Would do', dialog, selectedLaunchConfig);
        require(['views/dialogs/' + dialog], function(dialog) {
            new dialog({items: selectedLaunchConfig});
        });
    }
  });
})
(jQuery, window.eucalyptus ? window.eucalyptus : window.eucalyptus = {});
