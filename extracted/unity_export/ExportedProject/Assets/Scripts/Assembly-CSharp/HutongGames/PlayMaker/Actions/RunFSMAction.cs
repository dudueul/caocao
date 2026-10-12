using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class RunFSMAction : FsmStateAction
	{
		protected Fsm runFsm;

		public override void Reset()
		{
		}

		public override bool Event(FsmEvent fsmEvent)
		{
			return false;
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		public override void OnLateUpdate()
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

		public override void DoCollisionEnter(Collision collisionInfo)
		{
		}

		public override void DoCollisionStay(Collision collisionInfo)
		{
		}

		public override void DoCollisionExit(Collision collisionInfo)
		{
		}

		public override void DoParticleCollision(GameObject other)
		{
		}

		public override void DoControllerColliderHit(ControllerColliderHit collisionInfo)
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

		public override void DoCollisionEnter2D(Collision2D collisionInfo)
		{
		}

		public override void DoCollisionStay2D(Collision2D collisionInfo)
		{
		}

		public override void DoCollisionExit2D(Collision2D collisionInfo)
		{
		}

		public override void OnGUI()
		{
		}

		public override void OnExit()
		{
		}

		protected virtual void CheckIfFinished()
		{
		}
	}
}
