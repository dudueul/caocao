namespace HutongGames.PlayMaker.Actions
{
	public class DestroyObject : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		public FsmFloat delay;

		public FsmBool detachChildren;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
