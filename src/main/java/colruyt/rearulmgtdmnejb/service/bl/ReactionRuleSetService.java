package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.DeleteRuleSetInfoBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSetDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReaRulesetConverter;


/**
 * @version 1.0
 * @created 28-nov-2018 12:03:10
 */
@Stateless
@LocalBean
public class ReactionRuleSetService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReactionRuleSetService.class);

	@EJB
	private ReactionRuleSetDlService reactionRuleSetDlService;

	@EJB
	private ReaRulesetConverter reaRulesetConverter;

	/**
	 * This method is to create ruleSet
	 * 
	 * @param reactionRulesetBo
	 * @param isDuplicationAllowed
	 * @param logonId
	 * @return ReactionRulesetBo
	 * @throws ReaRuleManagementException
	 */
	public ReactionRulesetBo createReactionRuleSet(ReactionRulesetBo reactionRulesetBo, boolean isDuplicationAllowed,
			String userId) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_CREATEREACTIONRULESET);
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
			reaRuleset = reaRulesetConverter.convertReaRuleset(reaRuleset, reactionRulesetBo, userId);
			reaRuleset = reactionRuleSetDlService.createOrUpdate(reaRuleset);
			reactionRulesetBo.setRulesetId(reaRuleset.getReaRulesetId());
		} else {
			throw new ReaRuleManagementException("EN", ExceptionMessageConstants.MESSAGE_DUPLICATE_RULESET);
		}
		return reactionRulesetBo;
	}

	/**
	 * This method is to find ruleSet
	 * 
	 * @param cgChainId
	 * @param compChainId
	 * @return List<ReactionRulesetBo>
	 */
	public List<ReactionRulesetBo> find(long cgChainId, long compChainId) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_FINDREACTIONRULESET);
		List<ReactionRuleSet> ruleSetList = reactionRuleSetDlService.findByCgChainAndPCChain(cgChainId, compChainId);
		return reaRulesetConverter.convertRuleSetBo(ruleSetList);

	}

	/**
	 * This method is to validate the search rule input params
	 * 
	 * @param cgChainId
	 * @param compChainId
	 * @param ruleType
	 * @throws ReaRuleValidationException
	 */
	public void validateRuleSetInput(long cgChainId, long compChainId, String ruleType)
			throws ReaRuleValidationException {
		if (ruleType == null || ruleType.isEmpty()) {
			throw new ReaRuleValidationException(ReaRulMgtDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_RULETYPE_ABSENT);
		}
		if (cgChainId == 0) {
			throw new ReaRuleValidationException(ReaRulMgtDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_INVALID_CGCHAIN);
		}
		if (compChainId == 0) {
			throw new ReaRuleValidationException(ReaRulMgtDmnConstants.LANG_CODE_EN,
					ExceptionMessageConstants.MESSAGE_INVALID_PCCHAIN);
		}
	}

	/**
	 * This method is to get reaction ruleSet
	 * 
	 * @param rulesetId
	 * @return ReactionRulesetBo
	 * @throws ReaRuleManagementException
	 */
	public ReactionRulesetBo getReactionRuleset(long rulesetId) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVEREACTIONRULESET);
		ReactionRuleSet reactionRuleSet = reactionRuleSetDlService.findByPk(rulesetId);
		if (reactionRuleSet == null) {
			throw new ReaRuleManagementException("EN", ExceptionMessageConstants.MESSAGE_REACTION_RULESET_ABSENT);
		}
		return reaRulesetConverter.convertReactionRuleset(reactionRuleSet);

	}

	/**
	 * This method is to edit reaction ruleset
	 * 
	 * @param reactionRuleSetBo
	 */
	public void modifyRuleSetDetails(ReactionRulesetBo reactionRuleSetBo, String logonId)
			throws ReaRuleValidationException, ReaRuleManagementException {
		ReactionRuleSet existingReactionRuleset = reactionRuleSetDlService.findByPk(reactionRuleSetBo.getRulesetId());
		if (existingReactionRuleset == null) {
			throw new ReaRuleManagementException("EN", ExceptionMessageConstants.MESSAGE_REACTION_RULESET_ABSENT);
		}
		if(reactionRuleSetBo.getRefRuleTypeBo().getRuleTypeId() == 1  && (reactionRuleSetBo.getName().isEmpty() )){
			throw new ReaRuleManagementException("EN", ExceptionMessageConstants.MESSAGE_REACTION_RULESETNAME_ABSENT);
		}
		else{
		ReactionRuleSet reaRuleset = reaRulesetConverter.convertReaRuleset(existingReactionRuleset, reactionRuleSetBo,
				logonId);
		reactionRuleSetDlService.createOrUpdate(reaRuleset);
		}
		
	}
	/**
	 * This method is to delete reaction ruleset
	 * @param ruleSetId
	 * @param langCode
	 * @param logonId
	 * @throws ReaRuleManagementException
	 */
	public void logicallyDeleteReactionRuleSet(Long ruleSetId, String langCode, String logonId)
			throws ReaRuleManagementException {
		ReactionRuleSet reactionRuleSet = reactionRuleSetDlService.findByPk(ruleSetId);
		if (reactionRuleSet == null) {
			throw new ReaRuleManagementException(langCode, ExceptionMessageConstants.MESSAGE_REACTION_RULESET_ABSENT);
		}
		reactionRuleSet.setLstUpdateBy(logonId);
		reactionRuleSetDlService.logicallyDeleteRuleSet(reactionRuleSet);
	}
	
	public List<DeleteRuleSetInfoBo> findAllLogicallyDeletedRuleSet(Date dateDeleteRuleSetBefore) {
		List<DeleteRuleSetInfoBo> ruleSetList = reactionRuleSetDlService.findAllLogicallyDeletedRuleSet(dateDeleteRuleSetBefore);
		return ruleSetList;
	}
	
	public long physicalDeleteAllRuleSet(Set<Long> ruleSetIds){
		return reactionRuleSetDlService.physicalDeleteAllRuleSet(ruleSetIds);
	}
}