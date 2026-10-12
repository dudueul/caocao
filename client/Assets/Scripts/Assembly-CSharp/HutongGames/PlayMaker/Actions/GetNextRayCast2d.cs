using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetNextRayCast2d : FsmStateAction
	{
		public FsmOwnerDefault fromGameObject;

		public FsmVector2 fromPosition;

		public FsmVector2 direction;

		public Space space;

		public FsmFloat distance;

		public FsmInt minDepth;

		public FsmInt maxDepth;

		public FsmBool resetFlag;

		public FsmInt[] layerMask;

		public FsmBool invertMask;

		public FsmInt collidersCount;

		public FsmGameObject storeNextCollider;

		public FsmVector2 storeNextHitPoint;

		public FsmVector2 storeNextHitNormal;

		public FsmFloat storeNextHitDistance;

		public FsmFloat storeNextHitFraction;

		public FsmEvent loopEvent;

		public FsmEvent finishedEvent;

		private RaycastHit2D[] hits;

		private int colliderCount;

		private int nextColliderIndex;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetNextCollider()
		{
		}

		private RaycastHit2D[] GetRayCastAll()
		{
			return null;
		}
	}
}
