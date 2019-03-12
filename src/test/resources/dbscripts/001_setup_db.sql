--------------------------------------------------
-- Create Table REA_FLT_RULE
--------------------------------------------------
Create table REA_FLT_RULE (
    REA_RULE_ID                    DECIMAL(9)          NOT NULL   ,
    MAX_COMP_QTY                   DECIMAL(5, 3)                  ,
    X_TIME_QTY                     DECIMAL(5, 3)                  );
--------------------------------------------------
-- Create Table REA_PRD_ACT
--------------------------------------------------
Create table REA_PRD_ACT(
	REA_RULE_ID				DECIMAL(9)			NOT NULL,
	END_DT_DAYS				DECIMAL(9)					,
	MIN_DAYS				DECIMAL(9)	
);
--------------------------------------------------
-- Create Table REA_QTY_RULE
--------------------------------------------------
Create table REA_QTY_RULE(
	REA_RULE_ID				DECIMAL(9)		NOT NULL,
	QTY_COND_ID				DECIMAL(9)				,
	QTY_TYPE_ID				DECIMAL(9)

);
--------------------------------------------------
-- Create Table REA_REACTING_ACT
--------------------------------------------------
Create table REA_REACTING_ACT(
	REA_RULE_ID				DECIMAL(9)		NOT NULL,
	CATCH_ALL_YN			CHAR(1)					,
	REACTING_AMT			DECIMAL(10,3)			,
	REACTING_PERC			DECIMAL(10,3)			,
	THOLD_AMT				DECIMAL(10,3)			,
	THOLD_PERC				DECIMAL(10,3)			,
);
--------------------------------------------------
-- Create Table REA_RNF_ACT
--------------------------------------------------
Create table REA_RNF_ACT(
	REA_RULE_ID				DECIMAL(9)		NOT NULL,
	NO_OF_RNF				DECIMAL(2)		NOT NULL

);
--------------------------------------------------
-- Create Table REA_NREACT_ACT
--------------------------------------------------
Create table REA_NREACT_ACT(
	REA_RULE_ID				DECIMAL(9)		NOT NULL,
	FLTOUT_TYPE_ID			DECIMAL(3)		NOT NULL
	
);

--------------------------------------------------
-- Create Table REA_NREACT_SET_RSN
--------------------------------------------------
Create table REA_NREACT_SET_RSN(
	REA_RULE_ID				DECIMAL(9)		NOT NULL,
	REASON_ID			    DECIMAL(3)	    NOT NULL,
	LST_UPDATE_TS			TIMESTAMP(10)	NOT NULL
);

--------------------------------------------------
-- Create Table FLTOUT_TYPE_LANG
--------------------------------------------------
Create table FLTOUT_TYPE_LANG (
    FLTOUT_TYPE_ID                 DECIMAL(3)          NOT NULL    ,
    FLTOUT_TYPE_NAME               VARCHAR(25)         NOT NULL    ,
    DESCRIPTION                    VARCHAR(100)        NOT NULL	   ,  
    ISO_LANG_CODE				   CHAR(2)			   NOT NULL
);

--------------------------------------------------
-- Create Table QTY_COND_LANG
--------------------------------------------------
Create table QTY_COND_LANG (
    QTY_COND_ID                    DECIMAL(3)          NOT NULL    ,
    QTY_COND_NAME                  VARCHAR(25)         NOT NULL    ,
    DESCRIPTION                    VARCHAR(100)  				   ,
    ISO_LANG_CODE				   CHAR(2)			   NOT NULL
);

--------------------------------------------------
-- Create Table QTY_TYPE_LANG
--------------------------------------------------
Create table QTY_TYPE_LANG (
    QTY_TYPE_ID                    DECIMAL(3)          NOT NULL    ,
    QTY_TYPE_NAME                  VARCHAR(25)         NOT NULL    ,
    DESCRIPTION                    VARCHAR(100)        NOT NULL    ,
    ISO_LANG_CODE				   CHAR(2)			   NOT NULL
); 

--------------------------------------------------
-- Create Table REA_REASON_LANG
--------------------------------------------------
Create table REA_REASON_LANG (
    REASON_ID                      DECIMAL(3)          NOT NULL    ,
    REASON_NAME                    VARCHAR(25)         NOT NULL    ,
    DESCRIPTION                    VARCHAR(100)        NOT NULL    ,
    ISO_LANG_CODE				   CHAR(2)			   NOT NULL
); 

--------------------------------------------------
-- Create Table RULETYPE_LANG
--------------------------------------------------
Create table RULETYPE_LANG (
    RULETYPE_ID                    DECIMAL(3)          NOT NULL    ,
    RULETYPE_NAME                  VARCHAR(25)         NOT NULL    ,
    DESCRIPTION                    VARCHAR(100)        NOT NULL    ,
    ISO_LANG_CODE				   CHAR(2)			   NOT NULL
); 
--------------------------------------------------
-- Create Table REA_RULE_ACTTYPE
--------------------------------------------------
Create table REA_RULE_ACTTYPE (
    REA_RULE_ID                    DECIMAL(9)          NOT NULL    ,
    ACTION_TYPE_ID                 DECIMAL(3)         NOT NULL    
    
); 
--------------------------------------------------
-- Create Table REA_RULE
--------------------------------------------------
Create table REA_RULE (
    REA_RULE_ID                    DECIMAL(9)          NOT NULL    ,
    REA_RULESET_ID                 DECIMAL(9)          NOT NULL	   ,
    RULE_NAME                      VARCHAR(40)         NOT NULL    ,
    IC_FROM                    	   DECIMAL(3)          NOT NULL    ,
    IC_TO                    	   DECIMAL(3)          NOT NULL    ,
    DIRECT_YN                      CHAR(1)             NOT NULL    ,
    POSTPONED_YN                   CHAR(1)             NOT NULL    ,
    PERMENANT_YN                   CHAR(1)             NOT NULL    ,
    TEMPORARY_YN                   CHAR(1)             NOT NULL    ,
    VALID_FROM                     DATE(4)             			   ,
    VALID_UPTO                     DATE(4)                         ,
    RECALCULATE_YN                 CHAR(1)             NOT NULL    ,
    RULE_PRIORITY                  DECIMAL(3)              		   ,
    RULE_COMMENT                   VARCHAR(500)             	   ,
    CREATED_BY                     VARCHAR(11)          NOT NULL   ,
    LST_UPDATE_BY                  VARCHAR(11)          NOT NULL  ,
    C_REA_RULE_ID  					DECIMAL(9)          			,
    DATE_LOGICALLY_DELETED 			 DATE(4) ,
    LST_UPDATE_TS 					TIMESTAMP(10)
    
); 
--------------------------------------------------
-- Create Table REA_PPD_HCHY_ELMNT
--------------------------------------------------
Create table REA_PPD_HCHY_ELMNT (
    PPD_HCHY_ELMNT_ID                   DECIMAL(9)          NOT NULL   ,
    PPD_HCHY_TYPE_ID                 	DECIMAL(3)          NOT NULL   ,
    PPD_HCHY_VALUE                      VARCHAR(40)         NOT NULL   ,
    CREATED_BY                    	    VARCHAR(11)         NOT NULL   
   
);
--------------------------------------------------
-- Create Table REA_PPD_HCHYSET_ELMNT
--------------------------------------------------
Create table REA_PPD_HCHYSET_ELMNT (
    PPD_HCHYSET_ID                   DECIMAL(9)          NOT NULL   ,
    PPD_HCHY_ELMNT_ID                DECIMAL(9)          NOT NULL   ,
    LST_UPDATE_BY                    VARCHAR(11)         NOT NULL   
    
);
--------------------------------------------------
-- Create Table REA_RULE_SRC
--------------------------------------------------
Create table REA_RULE_SRC (
    REA_RULE_ID                   DECIMAL(9)          NOT NULL   ,
    SOURCE_ID               	  DECIMAL(3)          NOT NULL   
    
    
);
-- Create Table REA_RULESET
--------------------------------------------------
Create table REA_RULESET (
    REA_RULESET_ID                 DECIMAL(5)          NOT NULL    ,
    RULETYPE_ID                    DECIMAL(3)          NOT NULL    ,
    RULESET_NAME                   VARCHAR(40)                     ,
    CG_CHN_ID                      DECIMAL(5)          NOT NULL    ,
    COMP_CHAIN_ID                  DECIMAL(5)          NOT NULL    ,
    RULESET_COMMENT                VARCHAR(500)                    ,
    DATE_LOGICALLY_DELETED         DATE(4)                         ,
    LST_UPDATE_BY                  VARCHAR(11)         NOT NULL    ,
    LST_UPDATE_TS 				   TIMESTAMP(10)
);
--------------------------------------------------
-- Create Table REA_PPD_HCHYSET
--------------------------------------------------
Create table REA_PPD_HCHYSET (
    PPD_HCHYSET_ID                 DECIMAL(9)          NOT NULL   ,
    REA_RULE_ID                    DECIMAL(9)                     ,
    ASSORTMENT_NAME                VARCHAR(40)         NOT NULL   ,
    OWN_BRAND_YN                   CHAR(1)             NOT NULL   ,
    CHEAP_BRAND_YN                 CHAR(1)         	   NOT NULL   ,
    NAT_BRAND_YN                   CHAR(1)             NOT NULL   ,
    CREATED_BY                     VARCHAR(11)         NOT NULL   ,
    LST_UPDATE_BY                  VARCHAR(11)         NOT NULL
    
    
);
