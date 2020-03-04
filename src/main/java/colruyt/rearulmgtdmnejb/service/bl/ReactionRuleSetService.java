package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleSetInfoBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSetDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulesetConverter;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReactionRuleSetService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReactionRuleSetService.class);

	@EJB
	private ReactionRuleSetDlService reactionRuleSetDlService;
	@EJB
	private static ReferenceDataService referenceDataService;

	public ReactionRulesetBo createReactionRuleSet(ReactionRulesetBo reactionRulesetBo, boolean isDuplicationAllowed,
			String userId) throws ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_CREATEREACTIONRULESET);
		List<ReactionRuleSet> reactionRulesets = null;
		ReactionRuleSet reaRuleset = null;
		if (!isDuplicationAllowed && reactionRulesetBo.getRulesetId() == null) {
			reactionRulesets = reactionRuleSetDlService.findByAttributes(reactionRulesetBo.getColruytGroupChainId(),
					reactionRulesetBo.getPriceCompetitorChainId(),
					reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId());
		} else if (reactionRulesetBo.getRulesetId() != null) {
			reaRuleset = reactionRuleSetDlService.findByPk(reactionRulesetBo.getRulesetId());
		}
		if (reactionRulesets == null || reactionRulesets.isEmpty()) {
			reaRuleset = ReaRulesetConverter.convertFromBo(reaRuleset, reactionRulesetBo, userId);
			reaRuleset = reactionRuleSetDlService.createOrUpdate(reaRuleset);
			reactionRulesetBo.setRulesetId(reaRuleset.getReaRulesetId());
		} else {
			throw new ReaRuleManagementException(ReactionRuleDmnConstants.LANG_CODE_EN, ExceptionMessageConstants.MESSAGE_DUPLICATE_RULESET);
		}
		return reactionRulesetBo;
	}

	public List<ReactionRulesetBo> find(long cgChainId, long compChainId, String ruleType) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_FINDREACTIONRULESET);
		List<ReactionRuleSet> ruleSetList = reactionRuleSetDlService.findByAttributes(cgChainId, compChainId, RuleType.forValue(ruleType).getRuleTypeID());
		List<RefRuleTypeBo> refRuletype = referenceDataService.getAllRuleTypes();
		return ReaRulesetConverter.convertToBo(ruleSetList,refRuletype);

	}

	public void validateRuleSetInput(long cgChainId, long compChainId, String ruleType)
			throws ReaRuleValidationException {
		if (ruleType == null || ruleType.isEmpty()) {
			throw new ReaRuleValidationException(ReactionRuleDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_RULETYPE_ABSENT);
		}
		
		if (!RuleType.ruleTypeNames().contains(ruleType)) {
			throw new ReaRuleValidationException(ReactionRuleDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_INVALID_RULETYPE);
		}
		if (cgChainId == 0) {
			throw new ReaRuleValidationException(ReactionRuleDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_INVALID_CGCHAIN);
		}
		if (compChainId == 0) {
			throw new ReaRuleValidationException(ReactionRuleDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_INVALID_PCCHAIN);
		}
	}

	public ReactionRulesetBo getReactionRuleset(long rulesetId) throws ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RETRIEVEREACTIONRULESET);
		ReactionRuleSet reactionRuleSet = reactionRuleSetDlService.findByPk(rulesetId);
		if (reactionRuleSet == null) {
			throw new ReaRuleManagementException(ReactionRuleDmnConstants.LANG_CODE_EN, ExceptionMessageConstants.MESSAGE_REACTION_RULESET_ABSENT);
		}
		return ReaRulesetConverter.convertToBo(reactionRuleSet);

	}

	public void modifyRuleSetDetails(ReactionRulesetBo reactionRuleSetBo, String logonId)
			throws ReaRuleValidationException, ReaRuleManagementException {
		ReactionRuleSet existingReactionRuleset = reactionRuleSetDlService.findByPk(reactionRuleSetBo.getRulesetId());
		if (existingReactionRuleset == null) {
			throw new ReaRuleManagementException(ReactionRuleDmnConstants.LANG_CODE_EN, ExceptionMessageConstants.MESSAGE_REACTION_RULESET_ABSENT);
		}
		if (reactionRuleSetBo.getRefRuleTypeBo().getRuleTypeId() == 1 && (reactionRuleSetBo.getName().isEmpty())) {
			throw new ReaRuleManagementException(ReactionRuleDmnConstants.LANG_CODE_EN, ExceptionMessageConstants.MESSAGE_REACTION_RULESETNAME_ABSENT);
		} else {
			ReactionRuleSet reaRuleset = ReaRulesetConverter.convertFromBo(existingReactionRuleset, reactionRuleSetBo,
					logonId);
			reactionRuleSetDlService.createOrUpdate(reaRuleset);
		}

	}

	public void logicallyDeleteReactionRuleSet(Long ruleSetId, String langCode, String logonId)
			throws ReaRuleManagementException {
		ReactionRuleSet reactionRuleSet = reactionRuleSetDlService.findByPk(ruleSetId);
		if (reactionRuleSet == null) {
			throw new ReaRuleManagementException(langCode, ExceptionMessageConstants.MESSAGE_REACTION_RULESET_ABSENT);
		}
		reactionRuleSet.setLastUpdateBy(logonId);
		reactionRuleSetDlService.logicallyDeleteRuleSet(reactionRuleSet);
	}

	public List<DeleteRuleSetInfoBo> findAllLogicallyDeletedRuleSet(Date dateDeleteRuleSetBefore) {
		List<DeleteRuleSetInfoBo> ruleSetList = reactionRuleSetDlService
				.findAllLogicallyDeletedRuleSet(dateDeleteRuleSetBefore);
		return ruleSetList;
	}

	public void physicalDeleteAllRuleSet(Set<Long> ruleSetIds) {
		reactionRuleSetDlService.physicalDeleteAllRuleSet(ruleSetIds);
	}
}