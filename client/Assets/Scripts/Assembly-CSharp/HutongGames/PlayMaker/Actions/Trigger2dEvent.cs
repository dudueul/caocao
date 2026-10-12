using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Trigger2dEvent : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public Trigger2DType trigger;

		public FsmString collideTag;

		public FsmEvent sendEvent;

		public FsmGameObject storeCollider;

		private PlayMakerProxyBase cachedProxy;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		private void UpdateCallback()
		{
		}

		private void GetProxyComponent()
		{
		}

		private void AddCallback()
		{
		}

		private void RemoveCallback()
		{
		}

		private void StoreCollisionInfo(Collider2D collisionInfo)
		{
		}

		public override void DoTriggerEnter2D(Collider2D other)
		{
		}

		public override void DoTriggerStay2D(Collider2D other)
		{
		}

		public override void DoTriggerExit2D(Collider2D other)
		{
		}

		private void TriggerEnter2D(Collider2D other)
		{
		}

		private void TriggerStay2D(Collider2D other)
		{
		}

		private void TriggerExit2D(Collider2D other)
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
