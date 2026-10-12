namespace HutongGames.PlayMaker.Actions
{
	public class SetGameObject : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject variable;

		public FsmGameObject gameObject;

		public bool everyFrame;

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
