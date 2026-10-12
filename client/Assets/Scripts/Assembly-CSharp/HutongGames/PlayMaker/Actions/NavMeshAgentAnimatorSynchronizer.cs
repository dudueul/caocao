using UnityEngine;
using UnityEngine.AI;

namespace HutongGames.PlayMaker.Actions
{
	public class NavMeshAgentAnimatorSynchronizer : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		private Animator _animator;

		private NavMeshAgent _agent;

		private Transform _trans;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void DoAnimatorMove()
		{
		}
	}
}
