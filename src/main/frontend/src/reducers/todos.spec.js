import todos from './todos';
import * as types from '../constants/ActionTypes';

describe('todos reducer', () => {
  it('should handle initial state', () => {
    expect(
      todos(undefined, {})
    ).toEqual([])
  });

  it('should handle ADD_TODO', () => {
    expect(
      todos([], {
        type: types.ADD_TODO,
        todo: {
          id: 0,
          completed: false,
          text: 'Run the tests'
        }
      })
    ).toEqual([
      {
        text: 'Run the tests',
        completed: false,
        id: 0
      }
    ]);

    expect(
      todos([
        {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.ADD_TODO,
        todo: {
          id: 1,
          completed: false,
          text: 'Run the tests'
        }
      })
    ).toEqual([
      {
        text: 'Run the tests',
        completed: false,
        id: 1
      }, {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ]);

    expect(
      todos([
        {
          text: 'Run the tests',
          completed: false,
          id: 1
        }, {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.ADD_TODO,
        todo: {
          id: 2,
          completed: false,
          text: 'Fix the tests'
        }
      })
    ).toEqual([
      {
        text: 'Fix the tests',
        completed: false,
        id: 2
      }, {
        text: 'Run the tests',
        completed: false,
        id: 1
      }, {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ])
  });

  it('should handle DELETE_TODO', () => {
    expect(
      todos([
        {
          text: 'Run the tests',
          completed: false,
          id: 1
        }, {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.DELETE_TODO,
        id: 1
      })
    ).toEqual([
      {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ])
  });

  it('should handle EDIT_TODO', () => {
    expect(
      todos([
        {
          text: 'Run the tests',
          completed: false,
          id: 1
        }, {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.EDIT_TODO,
        todo: {
          text: 'Fix the tests',
          completed: false,
          id: 1
        }
      })
    ).toEqual([
      {
        text: 'Fix the tests',
        completed: false,
        id: 1
      }, {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ])
  });

  it('should handle COMPLETE_TODO', () => {
    expect(
      todos([
        {
          text: 'Run the tests',
          completed: false,
          id: 1
        }, {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.COMPLETE_TODO,
        todo: {
          text: 'Run the tests',
          completed: true,
          id: 1
        }
      })
    ).toEqual([
      {
        text: 'Run the tests',
        completed: true,
        id: 1
      }, {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ])
  });

  it('should handle COMPLETE_ALL', () => {
    expect(
      todos([
        {
          text: 'Run the tests',
          completed: true,
          id: 1
        }, {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.COMPLETE_ALL,
        todos: [
          {
            text: 'Run the tests',
            completed: true,
            id: 1
          }, {
            text: 'Use Redux',
            completed: true,
            id: 0
          }
        ]
      })
    ).toEqual([
      {
        text: 'Run the tests',
        completed: true,
        id: 1
      }, {
        text: 'Use Redux',
        completed: true,
        id: 0
      }
    ]);

    // Unmark if all todos are currently completed
    expect(
      todos([
        {
          text: 'Run the tests',
          completed: true,
          id: 1
        }, {
          text: 'Use Redux',
          completed: true,
          id: 0
        }
      ], {
        type: types.COMPLETE_ALL,
        todos: [
          {
            text: 'Run the tests',
            completed: false,
            id: 1
          }, {
            text: 'Use Redux',
            completed: false,
            id: 0
          }
        ]
      })
    ).toEqual([
      {
        text: 'Run the tests',
        completed: false,
        id: 1
      }, {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ])
  });

  it('should handle CLEAR_COMPLETED', () => {
    expect(
      todos([
        {
          text: 'Run the tests',
          completed: true,
          id: 1
        }, {
          text: 'Use Redux',
          completed: false,
          id: 0
        }
      ], {
        type: types.CLEAR_COMPLETED,
        todos: [
          {
            text: 'Use Redux',
            completed: false,
            id: 0
          }
        ]
      })
    ).toEqual([
      {
        text: 'Use Redux',
        completed: false,
        id: 0
      }
    ])
  });

  it('should not generate duplicate ids after CLEAR_COMPLETED', () => {
    expect(
      [
        {
          type: types.COMPLETE_TODO,
          todo: {
            id: 0
          }
        }, {
          type: types.CLEAR_COMPLETED,
          todos: [
            {
              text: 'Write tests',
              completed: false,
              id: 1
            }
          ]
        }, {
          type: types.ADD_TODO,
          todo: {
            id: 2,
            completed: false,
            text: 'Write more tests'
          }
        }
      ].reduce(todos, [
        {
          id: 0,
          completed: false,
          text: 'Use Redux'
        }, {
          id: 1,
          completed: false,
          text: 'Write tests'
        }
      ])
    ).toEqual([
      {
        text: 'Write more tests',
        completed: false,
        id: 2
      }, {
        text: 'Write tests',
        completed: false,
        id: 1
      }
    ])
  })
});
