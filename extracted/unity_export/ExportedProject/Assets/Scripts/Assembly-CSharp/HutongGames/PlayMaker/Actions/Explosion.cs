using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Explosion : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 center;

		[RequiredField]
		public FsmFloat force;

		[RequiredField]
		public FsmFloat radius;

		public FsmFloat upwardsModifier;

		public ForceMode forceMode;

		public FsmInt layer;

		public FsmInt[] layerMask;

		public FsmBool invertMask;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoExplosion()
		{
		}

		private bool ShouldApplyForce(GameObject go)
		{
			return false;
		}
	}
}
