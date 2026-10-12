using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class TriggerEvent : FsmStateAction
	{
		public FsmOwnerDefault gameObject;

		public TriggerType trigger;

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

		private void StoreCollisionInfo(Collider collisionInfo)
		{
		}

		public override void DoTriggerEnter(Collider other)
		{
		}

		public override void DoTriggerStay(Collider other)
		{
		}

		public override void DoTriggerExit(Collider other)
		{
		}

		private void TriggerEnter(Collider other)
		{
		}

		private void TriggerStay(Collider other)
		{
		}

		private void TriggerExit(Collider other)
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
